package hk.edu.polyu.comp.comp2021.simple.model;
import java.util.Scanner;
import java.util.HashMap;
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

    public abstract class Statement {
        public abstract void runState ();
    }

    public class vardef extends Statement {
        private String label;
        private String typ;
        private String varName;
        private String expRef;

        public static HashMap<String, Integer> intStorage = new HashMap<String, Integer>();
        public static HashMap<String, Boolean> boolStorage = new HashMap<String, Boolean>();

        public vardef (String label, String typ, String varName, String expRef) {
            this.label = label;
            this.typ = typ;
            this.varName = varName;
            this.expRef = expRef;
        }
        public String getLabel() {
            return label;
        }
        @Override
        public void runState() {
            if (this.typ.equals("bool")) {
                boolStorage.put(varName, Boolean.valueOf(expRef));
            }
            else if (this.typ.equals("int")) {
                intStorage.put(varName, Integer.valueOf(expRef));
            }
        }
    }

    



}
