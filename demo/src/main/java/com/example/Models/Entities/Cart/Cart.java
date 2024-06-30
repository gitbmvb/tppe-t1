package com.example.Models.Entities.Cart;

import java.util.ArrayList;
import com.example.Models.Entities.Product.Product;

public class Cart {
    private static Integer idCounter = 0;
    public Integer id = 0;
    public ArrayList<Product> products;
    public Double totalValue = 0.0;

    public Cart() {
        id = idCounter++;
        this.products = new ArrayList<Product>();
    }

    public void add(Product p) {
        this.products.add(p);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }
}
