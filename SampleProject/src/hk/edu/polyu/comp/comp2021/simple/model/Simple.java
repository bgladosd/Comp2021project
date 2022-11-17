package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.Scanner;

public class Simple {

    private Memory memory;
    private CommandBuilder commandBuilder;

    public Simple() {

        memory = new Memory();
        commandBuilder = new CommandBuilder();

        System.out.println("SIMPLE is running please input");
        while (true) {

            Scanner scanner1 = new Scanner(System.in); // Create a Scanner object
            System.out.print(">");
            String input = scanner1.nextLine(); // Read user input
            if(input.equals("quit"))break;
            commandBuilder.buildCommand(input, memory);

        }
    }
}
