package com.example.Models.Entities.Cart;

import java.util.ArrayList;
import com.example.Models.Entities.Product.Product;

public class Cart {
    private static Integer idCounter = 0;
    private Integer id = 0;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Integer totalItens = 0;
    private Double totalValue = 0.0;

    public Cart() {
        id = idCounter++;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
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
