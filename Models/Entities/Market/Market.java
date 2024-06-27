package Models.Entities.Market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Database.Database;
import Models.Entities.Client.DefaultClient;
import Models.Entities.Menu.Menu;

public class Market {
    public Integer menuHome() {
        Menu menu = new Menu("Menu Principal", new ArrayList<>(Arrays.asList("Cliente", "Vendas", "Produtos")));
        menu.setSelectedOption();
        return menu.getSelectedOption();
    }

    public void menuClient() {
        Menu menu = new Menu("Menu do Cliente", new ArrayList<>(Arrays.asList("Listar", "Adicionar", "Deletar")));
        menu.setSelectedOption();
        switch (menu.getSelectedOption()) {
            case 1:
                listClients();
                break;
            case 2:
                addClient();
                break;
            case 3:
                removeClient();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public void menuSales() {
        Menu menu = new Menu("Menu de Vendas", new ArrayList<>(Arrays.asList("Listar", "Adicionar", "Deletar")));
        menu.setSelectedOption();
        switch (menu.getSelectedOption()) {
            case 1:
                listSales();
                break;
            case 2:
                // addSale();
                break;
            case 3:
                removeSale();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public void menuProduct() {
        Menu menu = new Menu("Menu de Produtos", new ArrayList<>(Arrays.asList("Listar", "Adicionar", "Deletar")));
        menu.setSelectedOption();
        switch (menu.getSelectedOption()) {
            case 1:
                listProducts();
                break;
            case 2:
                // addProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public void listClients() {
        Database db = Database.getInstance();
        db.listClients();
    }

    public void listSales() {
        Database db = Database.getInstance();
        db.listSales();
    }

    public void listProducts() {
        Database db = Database.getInstance();
        db.listProducts();
    }

    public void addClient() {
        System.out.println("===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do cliente: ");
        String name = scanner.nextLine();
        System.out.println("Digite a sigla do estado do cliente: ");
        String state = scanner.nextLine();
        System.out.println("Digite o tipo de endereço do cliente (Capital ou Interior): ");
        String place = scanner.nextLine();

        // resolve isso daqui pra nois kaua pfv
        DefaultClient client = new DefaultClient(name, null);
        Database db = Database.getInstance();
        db.addClient(client);
        System.out.println("Cliente adicionado com sucesso!");
        System.out.println("===============");
    }

    public void removeClient() {
        System.out.println("===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do cliente que deseja remover: ");
        Integer id = scanner.nextInt();
        Database db = Database.getInstance();
        db.removeClient(id);
        System.out.println("Cliente removido com sucesso!");
        System.out.println("===============");
    }

    public void removeSale() {
        System.out.println("===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID da venda que deseja remover: ");
        Integer id = scanner.nextInt();
        Database db = Database.getInstance();
        db.removeSale(id);
        System.out.println("Venda removida com sucesso!");
        System.out.println("===============");
    }

    public void removeProduct() {
        System.out.println("===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do produto que deseja remover: ");
        Integer id = scanner.nextInt();
        Database db = Database.getInstance();
        db.removeProduct(id);
        System.out.println("Produto removido com sucesso!");
        System.out.println("===============");
    }

    public void run() {
        Integer option = -1;
        while (option != 0) {
            option = menuHome();
            switch (option) {
                case 0:
                    option = 0;
                    break;
                case 1:
                    menuClient();
                    break;
                case 2:
                    menuSales();
                    break;
                case 3:
                    menuProduct();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}
