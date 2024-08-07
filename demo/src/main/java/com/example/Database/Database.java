package com.example.Database;

import java.util.ArrayList;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;

public class Database {
    private static Database instance = null;
    private static ArrayList<Client> Clients;
    private static ArrayList<Product> Products;
    private static ArrayList<Sale> Sales;

    private Database() {
        Clients = new ArrayList<Client>();
        Products = new ArrayList<Product>();
        Sales = new ArrayList<Sale>();
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

    public void addClient(Client c) {
        Clients.add(c);
    }

    public void addClient(String name, EState state, EAddressPlace place) {
        DefaultClient client = new DefaultClient(name, state, place);
        Clients.add(client);
    }

    public void addDefaultClient(String name, EState state, EAddressPlace place) {
        DefaultClient client = new DefaultClient(name, state, place);
        Clients.add(client);
    }

    public void addPrimeClient(String name, EState state, EAddressPlace place) {
        PrimeClient client = new PrimeClient(name, state, place);
        Clients.add(client);
    }

    public Boolean checkIfClientExists(int id) {
        for (Client client : Clients) {
            if (client.getId() == id)
                return true;
        }
        return false;
    }

    public void removeClient(int id) {
        for (Client client : Clients) {
            if (client.getId() == id) {
                Clients.remove(client);
                break;
            }
        }
    }

    // Funções de produtos
    public Product getProduct(int id) {
        for (Product product : Products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void addProduct(Integer code, String name, String description, Double price, String unit, Integer amount) {
        Product product = new Product(code, name, description, price, unit, amount);
        Products.add(product);
    }

    public Boolean checkIfProductExists(int id) {
        for (Product product : Products) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    public Boolean checkIfProductExistsByCode(int code) {
        for (Product product : Products) {
            if (product.getInfo().getCode() == code)
                return true;
        }
        return false;
    }

    public Product getProductByCode(int code) {
        for (Product product : Products) {
            if (product.getInfo().getCode() == code)
                return product;
        }
        return null;
    }

    public void removeProduct(int id) {
        for (Product product : Products) {
            if (product.getId() == id) {
                Products.remove(product);
                break;
            }
        }
    }

    // Funções de vendas
    public ArrayList<Sale> getSales() {
        return Sales;
    }

    public void addSale(Client client, EPaymentMethod paymentMethod) {
        Sale sale = new Sale(client, paymentMethod);
        sale.setTotalValue();
        Sales.add(sale);
    }

    public void addSaleObject(Sale s){
        Sales.add(s);
    }

    public Boolean checkIfSaleExists(int id) {
        for (Sale sale : Sales) {
            if (sale.getId() == id)
                return true;
        }
        return false;
    }

    public void removeSale(int id) {
        for (Sale sale : Sales) {
            if (sale.getId() == id) {
                Sales.remove(sale);
                break;
            }
        }
    }
}