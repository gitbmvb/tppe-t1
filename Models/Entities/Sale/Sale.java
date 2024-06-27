package Models.Entities.Sale;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import Models.Entities.Abstract.Client;
import Models.Entities.Product.Product;
import Models.Enums.EPaymentMethod;
import Models.ValueObject.Taxes;

public class Sale {
    private Date data;
    private Client client;
    private ArrayList<Product> products;
    private EPaymentMethod paymentMethod;
    private Taxes taxes = new Taxes();

    public Sale(Client client, EPaymentMethod paymentMethod, ArrayList<Product> products) {
        this.data = Date.from(Instant.now());
        this.client = client;
        this.products = products;
        this.paymentMethod = paymentMethod;
    }

    public Double finish() {
        Double totalValue = this.sumProductsPriceAndTaxes();
        Double freigth = (this.client.getType().getCode() & 4) == 4 ? 0.0 : this.client.getAddress().calculateFreigth();
        totalValue = this.client.getType().applyDiscount(totalValue, freigth);
        if (paymentMethod == EPaymentMethod.CreditCard)
            totalValue *= 0.9;
        cashBackManipulation(totalValue);
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
        for (Product p : products) {
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
