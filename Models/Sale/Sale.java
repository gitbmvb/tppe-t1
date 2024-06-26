package Models.Sale;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import Models.Abstract.Client;
import Models.Enums.EPaymentMethod;
import Models.Product.Product;
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
        totalValue = applyDiscount(totalValue, freigth, paymentMethod);
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

    private Double applyDiscount(Double value, Double freigth, EPaymentMethod method) {
        if ((this.client.getType().getCode() & 1) == 0) {
            if (method == EPaymentMethod.CreditCard)
                value *= 0.9;
            return (value * 0.9) + (freigth * 0.7);
        }
        return value + freigth;
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
