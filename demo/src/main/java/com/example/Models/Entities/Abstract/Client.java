package com.example.Models.Entities.Abstract;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.example.Database.Database;
import com.example.Models.Entities.Cart.Cart;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EClientType;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;
import com.example.Models.ValueObject.CreditCard;

public abstract class Client {
    private static Integer idCounter = 0;
    private Integer id = 0;
    private String name;
    private Address address;
    private CreditCard creditCard;
    private Cart cart;
    protected EClientType type = EClientType.Default;
    protected Double cashBack = 0.0;

    public Client(String name, EState state, EAddressPlace place) {
        id = idCounter++;
        this.name = name;
        this.address = new Address(state, place);
        this.cart = new Cart();
    }

    @SuppressWarnings("deprecation")
    public boolean isNowSpecial() {
        Database db = Database.getInstance();
        ArrayList<Sale> sales = db.getSales();
        Double totalBuyed = 0.0;
        for (Sale s : sales)
            if (s.getClient() == this && s.getData().getMonth() == Date.from(Instant.now()).getMonth() - 1){
                s.setTotalValue();
                totalBuyed += s.getTotalValue();
            }
        return totalBuyed >= 100.0;
    }

    public void AddToCart(Product... product) {
        for(Product p : product){
            this.cart.add(p);
            this.cart.setTotalItens(this.cart.getTotalItens() + p.getAmount());
        }
    }

    public Double addCashback(Double value) {
        return this.cashBack;
    }

    public Double discountCashback(Double value) {
        return this.cashBack;
    }

    public Integer getId() {
        return id;
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

    public Cart getCart() {
        return cart;
    }
}