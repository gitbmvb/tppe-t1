package Models.Entities.Cart;

import java.util.ArrayList;

import Models.Entities.Abstract.Client;
import Models.Entities.Product.Product;

public class Cart {
    private static Integer idCounter = 0;
    public Integer id = 0;
    public ArrayList<Product> products;
    public Double totalValue = 0.0;

    public Cart(Client client) {
        id = idCounter++;
        this.products = new ArrayList<Product>();
    }
    
    public void add(Product p) {
        this.products.add(p);
    }
}
