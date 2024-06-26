package Models.Entities.Abstract;

import java.util.ArrayList;
import java.util.Random;

import Models.Entities.Product.Product;
import Models.Entities.Sale.Sale;
import Models.Enums.EClientType;
import Models.Enums.EPaymentMethod;
import Models.ValueObject.Address;
import Models.ValueObject.CreditCard;

public abstract class Client {
    private String name;
    private Address address;
    private CreditCard creditCard;
    private ArrayList<Product> products = new ArrayList<Product>();
    protected EClientType type = EClientType.Default;
    protected Double cashBack = 0.0;

    public Client(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void AddToCart(Product p) {
        this.products.add(p);
    }

    public Sale BuyCart(EPaymentMethod paymentMethod) {
        return new Sale(this, paymentMethod, products);
    }

    public Double addCashback(Double value) {
        return this.cashBack;
    }

    public Double discountCashback(Double value) {
        return this.cashBack;
    }

    public Double getCashBack() {
        return cashBack;
    }

    public CreditCard getCreditCard() {
        if (creditCard == null)
            creditCard = new CreditCard(GenerateCreditCardValue());
        return creditCard;
    }

    private String GenerateCreditCardValue() {
        String prefix = "4296 13";
        StringBuilder result = new StringBuilder(prefix);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            result.append(random.nextInt(10));
            if (i == 1 || i == 5 || i == 9)
                result.append(' ');
        }
        return result.toString();
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Double Discount(Double PartialValue) {
        return PartialValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EClientType getType() {
        return type;
    }

    public void setType(EClientType type) {
        this.type = type;
    }
}
