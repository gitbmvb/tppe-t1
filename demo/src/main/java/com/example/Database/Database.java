package com.example.Database;

import java.util.ArrayList;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;
import com.example.Models.ValueObject.ProductInfo;

public class Database {
    private static Database instance = null;
    private static ArrayList<Client> Clients;
    private static ArrayList<Product> Products;

    private Database() {
        Clients = new ArrayList<Client>();
        Products = new ArrayList<Product>();
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    // Funções de clientes
    public Client getClient(int id) {
        for (Client client : Clients) {
            if (client.getId() == id)
                return client;
        }
        return null;
    }
    
    public ArrayList<Client> getClients() {
        return Clients;
    }


    public void addDefaultClient(String name, EState state, EAddressPlace place) {
        DefaultClient client = new DefaultClient(name, new Address(state, place));
        Clients.add(client);
    }

    public void addPrimeClient(String name, EState state, EAddressPlace place) {
        PrimeClient client = new PrimeClient(name, new Address(state, place));
        Clients.add(client);
    }

   
    public void addProduct(Integer code, String name, String description, Double price, String unit) {
        ProductInfo prodinfo = new ProductInfo(code, name, description);
        Product product = new Product(prodinfo, price, unit, 0);
        Products.add(product);
    }

}