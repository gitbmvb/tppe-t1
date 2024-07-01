package com.example.Models.Entities.Market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import com.example.Database.Database;
import com.example.Models.Entities.Menu.Menu;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Cart.Cart;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;

public class Market {
    private Database db = Database.getInstance();

    // Menus
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
                addSale();
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
                addProduct();
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

    // CRUD de Clientes
    public void listClients() {
        System.out.println("===============");
        System.out.println("Listar Clientes");
        ArrayList<Client> clients = this.db.getClients();
        if(clients.size() == 0){
            System.out.println("Nenhum cliente cadastrado");
        } else {
            System.out.println("Listando clientes");
            for (Client client : clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Nome: " + client.getName() + "\n");
            }
        }
    }

    public void addClient() {
        System.out.println("===============");
        System.out.println("Adicionar Cliente");
        Scanner scanner = new Scanner(System.in);
        // Nome
        System.out.println("Nome: ");
        String name = scanner.nextLine();
        // Estado
        System.out.println("Estado (Sigla): ");
        EState stateEnum;
        try {
            stateEnum = EState.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido! Operação cancelada.");
            scanner.close();
            return;
        }
        // Lugar
        System.out.println("Lugar [Capital/Interior]: ");
        EAddressPlace placeEnum;
        try {
            placeEnum = EAddressPlace.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Lugar inválido! Operação cancelada.");
            scanner.close();
            return;
        }
        scanner.close();
        this.db.addClient(name, stateEnum, placeEnum);
        System.out.println("Cliente adicionado");
        System.out.println("===============");
    }

    public void removeClient() {
        System.out.println("===============");
        System.out.println("Remover Cliente");
        System.out.println("Digite o ID do cliente que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        scanner.close();
        this.db.removeClient(id);
        System.out.println("Cliente removido");
        System.out.println("===============");
    }

    // CRUD de Vendas
    public void addSale() {
        System.out.println("===============");
        System.out.println("Adicionar Venda");
        Scanner scanner = new Scanner(System.in);
        System.out.println("ClienteID: ");
        Integer clientId = scanner.nextInt();
        Client client = this.db.getClient(clientId);
        System.out.println("Quantidade de Produtos Diferentes: ");
        Integer quantity = scanner.nextInt();
        System.out.println("Para cada produto, digite seu ID e a quantidade: ");
        Cart cart = new Cart();
        for (int i = 0; i < quantity; i++) {
            System.out.println("ProdutoID: ");
            Integer productId = scanner.nextInt();
            System.out.println("Quantidade: ");
            Integer productQuantity = scanner.nextInt();

            Product product = this.db.getProduct(productId);
            product.setAmount(productQuantity);
            cart.add(product);
        }
        System.out.println("Pagamento [CreditCard, Pix, CashBack]: ");
        EPaymentMethod paymentMethod;
        try {
            paymentMethod = EPaymentMethod.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido! Operação cancelada.");
            scanner.close();
            return;
        }
        scanner.close();
        this.db.addSale(client, cart, paymentMethod);
        System.out.println("Venda adicionada");
        System.out.println("===============");
    }

    public void listSales() {
        System.out.println("===============");
        System.out.println("Listar Vendas");
        ArrayList<Sale> sales = this.db.getSales();
        if (sales.size() == 0)
            System.out.println("Nenhuma venda cadastrada.");
        else {
            System.out.println("Listando vendas");
            for (Sale sale : sales) {
                System.out.println("Cliente: " + sale.getClient().getName());
                System.out.println("Data: " + sale.getData());
                System.out.println("Valor: " + sale.getTotalValue());
                System.out.println("Itens: " + sale.getCart().getProducts().size() + "\n");
            }
        }
    }

    public void removeSale() {
        System.out.println("===============");
        System.out.println("Remover Venda");
        System.out.println("Digite o ID da venda que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        scanner.close();
        this.db.removeSale(id);
        System.out.println("Venda removida");
        System.out.println("===============");
    }

    // CRUD de Produtos
    public void addProduct(){
        System.out.println("===============");
        System.out.println("Adicionar Produto");
        Scanner scanner = new Scanner(System.in);
        // Código
        System.out.println("Código: ");
        Integer code = scanner.nextInt();
        scanner.nextLine();
        // Nome
        System.out.println("Nome: ");
        String name = scanner.nextLine();
        // Descrição
        System.out.println("Descrição: ");
        String description = scanner.nextLine();
        // Preço
        System.out.println("Preço: ");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        // Unidade
        System.out.println("Unidade: ");
        String unit = scanner.nextLine();
        // Quantidade
        System.out.println("Quantidade: ");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        this.db.addProduct(code, name, description, price, unit, quantity);        
        System.out.println("Produto adicionado");
        System.out.println("===============");
    }

    public void listProducts() {
        System.out.println("===============");
        System.out.println("Listar Produtos");
        ArrayList<Product> products = this.db.getProducts();
        if(products.size() == 0){
            System.out.println("Nenhum produto cadastrado");
        } else {
            System.out.println("Listando produtos");
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Nome: " + product.getInfo().getName());
                System.out.println("Preço: " + product.getPrice() + "\n");
            }
        }
        System.out.println("===============");
    }

    public void removeProduct() {
        System.out.println("===============");
        System.out.println("Remover Produto");
        System.out.println("Digite o ID do produto que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        scanner.close();
        this.db.removeProduct(id);
        System.out.println("Produto removido");
        System.out.println("===============");
    }

    public void run() {
        Integer option = -1;
        while (option != 0) {
            option = menuHome();
            switch (option) {
                case 0:
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
