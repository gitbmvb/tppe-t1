package Models.Entities.Market;

import java.util.Scanner;

import Database.Database;
import Models.Entities.Abstract.Client;
import Models.Entities.Client.DefaultClient;

public class Market {

   public void menuClient(){
       System.out.println("===============");
        System.out.println("Menu do Cliente");
        System.out.println("1 - Listar");
        System.out.println("2 - Adicionar");
        System.out.println("3 - Deletar");
        System.out.println("0 - Voltar");
        System.out.println("===============");
        System.out.println("Opção: ");
        Scanner scanner = new Scanner(System.in);
        Integer option = scanner.nextInt();
        switch(option){
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
   
    public void listClients(){
        System.out.println("===============");
        Database db = Database.getInstance();
        Integer size = db.getClients().size();
        if(size == 0)
        System.out.println("Nenhum cliente cadastrado.");
        else {
            System.out.println("Listando clientes");
            for(Client client : db.getClients()){
                System.out.println("ID: " + client.getId());
                System.out.println("Nome: " + client.getName() + "\n");
            }
        }
        System.out.println("===============");
   }

   public void addClient(){
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

   public void removeClient(){
        System.out.println("===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do cliente que deseja remover: ");
        Database db = Database.getInstance();
        db.removeClient(scanner.nextInt());
        System.out.println("Cliente removido com sucesso!");
        System.out.println("===============");
   }

   public void menuSales(){
       System.out.println("===============");
        System.out.println("Menu de Vendas");
        System.out.println("1 - Listar");
        System.out.println("2 - Adicionar");
        System.out.println("3 - Deletar");
        System.out.println("0 - Voltar");
        System.out.println("===============");
        System.out.println("Opção: ");
        Scanner scanner = new Scanner(System.in);
        Integer option = scanner.nextInt();
        // switch(option){
        //     case 1:
        //         listSales();
        //         break;
        //     case 2:
        //         addSale();
        //         break;
        //     case 3:
        //         removeSale();
        //         break;
        //     case 0:
        //         break;
        //     default:
        //         System.out.println("Opção inválida");
        //         break;
        // }
   }

   public Integer menuHome(){
       System.out.println("===============");
        System.out.println("Menu Principal");
        System.out.println("1 - Cliente");
        System.out.println("2 - Vendas");
        System.out.println("0 - Sair");
        System.out.println("===============");
        System.out.println("Opção: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
   }

   public void run(){
        Integer option = -1;
        while(option != 0){
            option = menuHome();
            switch(option){
                case 1:
                    menuClient();
                    break;
                case 2:
                    menuSales();
                    break;
                case 0:
                    option = 0;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
   }
}
