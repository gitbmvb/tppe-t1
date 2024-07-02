package com.example.Models.Entities.Cart;

import java.util.ArrayList;
import com.example.Models.Entities.Product.Product;

public class Cart {
    private static Integer idCounter = 0;
    public Integer id = 0;
    public ArrayList<Product> products;
    public Integer totalItens = 0;
    public Double totalValue = 0.0;

    public Cart(ArrayList<Product> products) {
        id = idCounter++;
        this.products = products;
    }

    public void setTotalValue() {
        for(Product p : products) {
            totalValue += (p.getPrice() * p.getAmount());
        }
    }

    public void setTotalItens() {
        for(Product p : products) {
            totalItens += p.getAmount();
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void add(Product p) {
        this.products.add(p);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    
}
