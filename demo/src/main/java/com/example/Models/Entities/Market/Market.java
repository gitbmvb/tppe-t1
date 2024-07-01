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

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void showTitle(String title){
        System.out.println("===============");
        System.out.println(title);
    }

    public void pressEnterToContinue(String message) {
        System.out.println(message);
        System.out.println("\nAperte Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Menus
    public Integer menuHome() {
        clearScreen();
        Menu menu = new Menu("Menu Principal", new ArrayList<>(Arrays.asList("Cliente", "Vendas", "Produtos")));
        menu.setSelectedOption();
        return menu.getSelectedOption();
    }

    public void menuClient() {
        clearScreen();
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
        clearScreen();
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
        clearScreen();
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
        clearScreen();
        showTitle("Lista de Clientes");
        ArrayList<Client> clients = this.db.getClients();
        if(clients.size() == 0){
            System.out.println("**Nenhum cliente cadastrado**");
        } else {
            for (Client client : clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Nome: " + client.getName() + "\n");
            }
        }
        pressEnterToContinue("");
    }

    public void addClient() {
        clearScreen();
        showTitle("Adicionar Cliente");
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
            // scanner.close();
            return;
        }
        // Lugar
        System.out.println("Lugar [Capital/Interior]: ");
        EAddressPlace placeEnum;
        try {
            placeEnum = EAddressPlace.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Lugar inválido! Operação cancelada.");
            // scanner.close();
            return;
        }
        // scanner.close();
        this.db.addClient(name, stateEnum, placeEnum);
        clearScreen();
        pressEnterToContinue("Cliente " + name + " adicionado!");
    }

    public void removeClient() {
        clearScreen();
        System.out.println("===============");
        System.out.println("Remover Cliente");
        System.out.println("Digite o ID do cliente que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        if (this.db.checkIfClientExists(id)){
            this.db.removeClient(id);
            pressEnterToContinue("O cliente foi removido com sucesso!");
        } else {
            pressEnterToContinue("Cliente não encontrado!");
        }
    }

    // CRUD de Vendas
    public void addSale() {
        clearScreen();
        showTitle("Adicionar Venda");
        Scanner scanner = new Scanner(System.in);
        System.out.println("ClienteID: ");
        Integer clientId = scanner.nextInt();
        scanner.nextLine();
        if(!this.db.checkIfClientExists(clientId)){
            System.out.println("Cliente não encontrado!");
            return;
        }
        Client client = this.db.getClient(clientId);

        System.out.println("Quantidade de Produtos Diferentes: ");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Para cada produto, digite seu ID e a quantidade: ");
        Cart cart = new Cart();
        for (int i = 0; i < quantity; i++) {
            System.out.println("Código do Produto: ");
            Integer productCode = scanner.nextInt();
            scanner.nextLine();
            if(!this.db.checkIfProductExistsByCode(productCode)){
                System.out.println("Produto não encontrado!");
                return;
            }
            System.out.println("Quantidade: ");
            Integer productQuantity = scanner.nextInt();
            scanner.nextLine();
            Product product = this.db.getProductByCode(productCode);
            product.setAmount(productQuantity);
            cart.add(product);
            cart.totalItens += productQuantity;
        }
        System.out.println("Pagamento [CreditCard, Pix, CashBack]: ");
        EPaymentMethod paymentMethod;
        try {
            paymentMethod = EPaymentMethod.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido! Operação cancelada.");
            return;
        }
        this.db.addSale(client, cart, paymentMethod);
        pressEnterToContinue("Venda adicionada com sucesso!");
    }

    public void listSales() {
        clearScreen();
        showTitle("Listar Vendas");
        ArrayList<Sale> sales = this.db.getSales();
        if (sales.size() == 0)
            System.out.println("Nenhuma venda cadastrada.");
        else {
            for (Sale sale : sales) {
                System.out.println("Cliente: " + sale.getClient().getName());
                System.out.println("Data: " + sale.getData());
                System.out.println("Valor: " + sale.getTotalValue());
                System.out.println("Itens: " + sale.getCart().getProducts().size() + "\n");
            }
        }
        pressEnterToContinue("");
    }

    public void removeSale() {
        clearScreen();
        showTitle("Remover Venda");
        System.out.println("Digite o ID da venda que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        if(this.db.checkIfSaleExists(id)){
            this.db.removeSale(id);
            pressEnterToContinue("Venda removida!");
        } else {
            pressEnterToContinue("Venda não encontrada!");
        }
    }

    // CRUD de Produtos
    public void addProduct(){
        clearScreen();
        showTitle("Adicionar Produto");
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
        this.db.addProduct(code, name, description, price, unit);
        pressEnterToContinue("Produto adicionado!");
    }

    public void listProducts() {
        clearScreen();
        showTitle("Listar Produtos");
        ArrayList<Product> products = this.db.getProducts();
        if(products.size() == 0){
            System.out.println("**Nenhum produto cadastrado**");
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Nome: " + product.getInfo().getName());
                System.out.println("Preço: " + product.getPrice() + "\n");
            }
        }
        pressEnterToContinue("");
    }

    public void removeProduct() {
        clearScreen();
        showTitle("Remover Produto");
        System.out.println("Digite o ID do produto que deseja remover: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        if(this.db.checkIfProductExists(id)){
            this.db.removeProduct(id);
            pressEnterToContinue("Produto removido!");
        } else {
            pressEnterToContinue("Produto não encontrado!");
        }
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
