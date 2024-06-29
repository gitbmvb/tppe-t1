package com.example.Database;

import java.util.ArrayList;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;

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

    public ArrayList<Client> getClients() {
        return Clients;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public ArrayList<Sale> getSales() {
        return Sales;
    }

    public void addClient(Client client) {
        Clients.add(client);
    }

    public void addProduct(Product product) {
        Products.add(product);
    }

    public void addSale(Sale sale) {
        Sales.add(sale);
    }

    public void listClients() {
        System.out.println("===============");
        Integer size = Clients.size();
        if (size == 0)
            System.out.println("Nenhum cliente cadastrado.");
        else {
            System.out.println("Listando clientes");
            for (Client client : Clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Nome: " + client.getName() + "\n");
            }
        }
        System.out.println("===============");
    }

    public void listProducts() {
        System.out.println("===============");
        Integer size = Products.size();
        if (size == 0)
            System.out.println("Nenhum produto cadastrado.");
        else {
            System.out.println("Listando produtos");
            for (Product product : Products) {
                System.out.println("ID: " + product.id);
                System.out.println("Nome: " + product.getInfo().getName());
                System.out.println("Preço: " + product.getPrice() + "\n");
            }
        }
        System.out.println("===============");
    }

    public void listSales() {
        System.out.println("===============");
        Integer size = Sales.size();
        if (size == 0)
            System.out.println("Nenhuma venda cadastrada.");
        else {
            System.out.println("Listando vendas");
            for (Sale sale : Sales) {
                System.out.println("ID: " + sale.id);
                System.out.println("Cliente: " + sale.getClient().getName());
                // System.out.println("Valor: " + sale.getTotal() + "\n");
            }
        }
        System.out.println("===============");
    }

    public void removeClient(int id) {
        for (Client client : Clients) {
            if (client.getId() == id) {
                Clients.remove(client);
                break;
            }
        }
    }

    public void removeProduct(int id) {
        for (Product product : Products) {
            if (product.id == id) {
                Products.remove(product);
                break;
            }
        }
    }

    public void removeSale(int id) {
        for (Sale sale : Sales) {
            if (sale.id == id) {
                Sales.remove(sale);
                break;
            }
        }
    }
}