package com.example.Models.Entities.Product;

import com.example.Models.ValueObject.ProductInfo;

public class Product {
    private static Integer idCounter = 0;
    private Integer id = 0;
    private ProductInfo Info;
    private Double price;
    private String unit;
    private Integer amount;

    public Product(ProductInfo info, Double price, String unit, int amount) {
        id = idCounter++;
        this.price = price;
        this.unit = unit;
        this.amount = amount;
        this.Info = info;
    }

    public void setInfo(ProductInfo info) {
        Info = info;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public ProductInfo getInfo() {
        return Info;
    }


    public Double getPrice() {
        return price;
    }


    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }
}