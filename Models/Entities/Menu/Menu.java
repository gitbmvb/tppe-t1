package Models.Entities.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String title;
    private ArrayList<String> options;
    private Integer selectedOption;

    public Menu(String title, ArrayList<String> options) {
        this.title = title;
        this.options = options;
        this.options.add(0, "Voltar/Sair");
        System.out.println("===============");
        for(int i = 0; i < options.size(); i++){
            System.out.println(i + " - " + options.get(i));
        } 
        System.out.println("===============");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setSelectedOption() {
        System.out.println("Opção: ");
        Scanner scanner = new Scanner(System.in);
        this.selectedOption = scanner.nextInt();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public Integer getSelectedOption() {
        return selectedOption;
    }
}
