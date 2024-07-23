package com.example.Models.Entities.Sale;

import java.time.Instant;
import java.util.Date;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.ValueObject.Taxes;

public class Sale {
    private static Integer idCounter = 0;
    private Integer id = 0;
    private Date data;
    private Client client;
    private EPaymentMethod paymentMethod;
    private Taxes taxes = new Taxes();
    private Double totalValue = 0.0;

    public Sale() {
    }

    public Sale(Client client, EPaymentMethod paymentMethod) {
        id = idCounter++;
        this.data = Date.from(Instant.now());
        this.client = client;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue() {
        for (Product p : client.getCart().getProducts()) {
            taxes.calculateTaxes(client.getAddress(), p.getPrice());
            totalValue += (p.getPrice() * p.getAmount());
        }
    }

    public Double calculateFreigth() {
        EAddressPlace place = this.getClient().getAddress().getPlace();
        Integer region = this.getClient().getAddress().getState().getRegion();
        Double value = 0.0;
        if (place == EAddressPlace.Capital) {
            switch (region) {
                case 0:
                    value = 5.0;
                    break;
                case 1:
                    value = 20.0;
                    break;
                case 2:
                    value = 15.0;
                    break;
                case 3:
                    value = 7.0;
                    break;
                case 4:
                    value = 10.0;
                    break;
                case 5:
                    value = 10.0;
                    break;
            }
        }
        if (place == EAddressPlace.Inside) {
            switch (region) {
                case 1:
                    value = 25.0;
                    break;
                case 2:
                    value = 18.0;
                    break;
                case 3:
                    value = 10.0;
                    break;
                case 4:
                    value = 13.0;
                    break;
                case 5:
                    value = 13.0;
                    break;
            }
        }
        return (this.getClient().getType().getCode() & 4) == 4 ? 0.0 : value;
    }

    public Double finish() {
        FinishSaleMethod finishMethod = new FinishSaleMethod(this);
        return finishMethod.finish();
    }

    public void cashBackManipulation(Double value) {
        if (paymentMethod == EPaymentMethod.CashBack) {
            this.client.discountCashback(value);
            return;
        }
        this.client.addCashback(value);
    }

    public double sumProductsPriceAndTaxes() {
        double sum = 0.0;
        for (Product p : client.getCart().getProducts()) {
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

    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Taxes getTaxes() {
        return taxes;
    }
}
