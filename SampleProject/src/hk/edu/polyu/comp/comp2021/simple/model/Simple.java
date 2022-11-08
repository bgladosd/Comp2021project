package hk.edu.polyu.comp.comp2021.simple.model;
import java.util.Scanner;
public class Simple {

    public Simple(){
        System.out.println("SIMPLE is running please input");
        while (true){
            Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
            System.out.print(">");

            String input = scanner1.nextLine();  // Read user input
            System.out.println("your input is: " + input);  // Output user input



        }
    }

}
