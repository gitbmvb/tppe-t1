package Database;

import java.util.ArrayList;

import Models.Entities.Abstract.Client;
import Models.Entities.Product.Product;
import Models.Entities.Sale.Sale;

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
        if (instance == null) instance = new Database();
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

    public void removeClient(int id) {
        for (Client client : Clients) {
            if (client.id == id) {
                Clients.remove(client);
                break;
            }
        }
    }
    
    public void addProduct(Product product) {
        Products.add(product);
    }
    
    public void removeProduct(int id) {
        for (Product product : Products) {
            if (product.id == id) {
                Products.remove(product);
                break;
            }
        }
    }

    public void addSale(Sale sale) {
        Sales.add(sale);
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