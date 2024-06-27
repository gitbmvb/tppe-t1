package Models.Entities.Product;

import Models.ValueObject.ProductInfo;

public class Product {
    private ProductInfo Info;
    private Double price;
    private String unit;
    private int amount;

    public Product(ProductInfo info, Double price, String unit, int amount) {
        this.price = price;
        this.unit = unit;
        this.amount = amount;
        this.Info = info;
    }

    public ProductInfo getInfo() {
        return Info;
    }

    public void setInfo(ProductInfo info) {
        Info = info;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
