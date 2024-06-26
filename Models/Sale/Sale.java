package Models.Sale;

import java.util.ArrayList;
import java.util.Date;

import Models.Abstract.Client;
import Models.Product.Product;

public class Sale {
    private Date data;
    private Client client;
    private ArrayList<Product> products;

    // add pattern
    private String paymentMethod;
}
