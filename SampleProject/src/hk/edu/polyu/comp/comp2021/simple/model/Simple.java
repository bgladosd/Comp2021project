package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.Scanner;
/** The SIMPLE program class that use memory class and command builder class to start the program process
 * @author Jack Lee
 */
public class Simple {

    private Memory memory;
    private CommandBuilder commandBuilder;
    /** construct the SIMPLE object, set up memory and commandbuilder, start accept inputs*/
    public Simple() {

        memory = new Memory();
        commandBuilder = new CommandBuilder();

        System.out.println("SIMPLE is running please input");
        while (true) {

            Scanner scanner1 = new Scanner(System.in);
            System.out.print(">");
            String input = scanner1.nextLine();
            if (input.equals("quit"))
                break;
            commandBuilder.buildCommand(input, memory);

        }
    }
}
