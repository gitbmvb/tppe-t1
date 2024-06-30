package com.example.Models.Entities.Sale;

import java.time.Instant;
import java.util.Date;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Cart.Cart;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.ValueObject.Taxes;

public class Sale {
    private static Integer idCounter = 0;
    private Integer id = 0;
    private Date data;
    private Client client;
    private Cart cart;
    private EPaymentMethod paymentMethod;
    private Taxes taxes = new Taxes();
    private Double totalValue = 0.0;

    public Sale(Client client, EPaymentMethod paymentMethod, Cart cart) {
        id = idCounter++;
        this.data = Date.from(Instant.now());
        this.client = client;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double finish() {
        Double totalValue = this.sumProductsPriceAndTaxes();
        Double freigth = (this.client.getType().getCode() & 4) == 4 ? 0.0 : this.client.getAddress().calculateFreigth();
        totalValue = this.client.getType().applyDiscount(totalValue, freigth);
        if (paymentMethod == EPaymentMethod.CreditCard)
            totalValue *= 0.9;
        cashBackManipulation(totalValue);
        this.totalValue = totalValue;
        return totalValue;
    }

    private void cashBackManipulation(Double value) {
        if (paymentMethod == EPaymentMethod.CashBack) {
            this.client.discountCashback(value);
            return;
        }
        this.client.addCashback(value);
    }

    private double sumProductsPriceAndTaxes() {
        double sum = 0.0;
        for (Product p : cart.products) {
            taxes.calculateTaxes(this.client.getAddress(), p.getPrice());
            sum += p.getPrice() + taxes.getIcms() + taxes.getMunicipal();
        }

        return sum;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
