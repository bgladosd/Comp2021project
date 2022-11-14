package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.HashMap;
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
            commandBuilder.buildCommand(input, memory);

            // Store <label, statement (class)>
            HashMap<String, Statement> StateStorage = new HashMap<String, Statement>();
            // Store <expression name, expression (class)>
            HashMap<String, expression> ExpStorage = new HashMap<String, expression>();

        }
    }

    public static boolean checkIsValidNameOrLabel(String s) {
        // ascii table [(48)0-9(58),(65)A-Z(90),(97)a-z(122)]
        // case1 if more than eight characters
        if (s.length() > 8) {
            return false;
        }
        // case2 if not english letters and digits
        for (char c : s.toCharArray()) {
            if (!((c > 47 && c < 59) || (c > 64 && c < 91) || (c > 96 && c < 123))) {
                return false;
            }

        }

        // case3 if string start with 0-9
        if (s.charAt(0) > 47 && s.charAt(0) < 58) {
            return false;
        }
        // case4 is SIMPLE Keywords
        // please continue add
        String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "inspect", "if", "load", "execute" };
        for (String string : Identifiers) {
            if (s.equals(string)) {
                return false;
            }
        }

        return true;

    }

    public abstract class Statement {
        private String label;

        public abstract void runState();
    }

    public HashMap<String, Integer> intStorage = new HashMap<String, Integer>();
    public HashMap<String, Boolean> boolStorage = new HashMap<String, Boolean>();

    public class vardef extends Statement {
        private String typ;
        private String varName;
        private String expRef;

        public vardef(String label, String typ, String varName, String expRef) {
            super.label = label;
            this.typ = typ;
            this.varName = varName;
            this.expRef = expRef;
        }

        public String getLabel() {
            return super.label;
        }

        @Override
        public void runState() {
            if (this.typ.equals("bool")) {
                boolStorage.put(varName, Boolean.valueOf(expRef));
            } else if (this.typ.equals("int")) {
                intStorage.put(varName, Integer.valueOf(expRef));
            }
        }
    }

    public abstract class expression {
        private String expName;

        public abstract String calculate();
    }

    public HashMap<String, expression> expStorage = new HashMap<String, expression>();

    public class binexpr extends expression {
        private String expRef1;
        private String bop;
        private String expRef2;

        public binexpr(String expName, String expRef1, String bop, String expRef2) {
            super.expName = expName;
            this.expRef1 = expRef1;
            this.expRef2 = expRef2;
            this.bop = bop;
        }

        public String getExpName() {
            return super.expName;
        }

        @Override
        public String calculate() {
            int intExpRef1 = 0, intExpRef2 = 0;
            boolean boolExpRef1 = true, boolExpRef2 = true, ref1IsInt = false;
            if (intStorage.get(expRef1) != null) {
                intExpRef1 = intStorage.get(expRef1);
                ref1IsInt = true;
            } else if (boolStorage.get(expRef1) != null) {
                boolExpRef1 = boolStorage.get(expRef1);
            } else if (expRef1.equals("true") || expRef1.equals("false")) {
                boolExpRef1 = Boolean.parseBoolean(expRef1);
            } else {
                intExpRef1 = Integer.parseInt(expRef1);
                ref1IsInt = true;
            }

            if (intStorage.get(expRef2) != null) {
                intExpRef2 = intStorage.get(expRef2);
            } else if (boolStorage.get(expRef2) != null) {
                boolExpRef2 = boolStorage.get(expRef2);
            } else if (expRef2.equals("true") || expRef2.equals("false")) {
                boolExpRef2 = Boolean.parseBoolean(expRef2);
            } else {
                intExpRef2 = Integer.parseInt(expRef2);
            }

            if (ref1IsInt) {
                switch (bop) {
                    case "%":
                        return String.valueOf((intExpRef1 % intExpRef2));
                    case "+":
                        return String.valueOf((intExpRef1 + intExpRef2));
                    case "-":
                        return String.valueOf((intExpRef1 - intExpRef2));
                    case "*":
                        return String.valueOf((intExpRef1 * intExpRef2));
                    case "/":
                        return String.valueOf((intExpRef1 / intExpRef2));
                    case ">":
                        return String.valueOf((intExpRef1 > intExpRef2));
                    case ">=":
                        return String.valueOf((intExpRef1 >= intExpRef2));
                    case "<":
                        return String.valueOf((intExpRef1 < intExpRef2));
                    case "<=":
                        return String.valueOf((intExpRef1 <= intExpRef2));
                    case "==":
                        return String.valueOf((intExpRef1 == intExpRef2));
                    case "!=":
                        return String.valueOf((intExpRef1 != intExpRef2));
                }
            } else {
                switch (bop) {
                    case "==":
                        return String.valueOf((boolExpRef1 == boolExpRef2));
                    case "!=":
                        return String.valueOf((boolExpRef1 != boolExpRef2));
                    case "||":
                        return String.valueOf((boolExpRef1 || boolExpRef2));
                    case "&&":
                        return String.valueOf((boolExpRef1 && boolExpRef2));
                }
            }
            return "";
        }

    }

    public class unexpr extends expression {
        private String expRef1;
        private String uop;

        public unexpr(String expName, String expRef1, String uop) {
            super.expName = expName;
            this.expRef1 = expRef1;
            this.uop = uop;
        }

        public String getExpName() {
            return super.expName;
        }

        @Override
        public String calculate() {
            int intExpRef1 = 0;
            boolean boolExpRef1 = true, ref1IsInt = false;
            if (intStorage.get(expRef1) != null) {
                intExpRef1 = intStorage.get(expRef1);
                ref1IsInt = true;
            } else if (boolStorage.get(expRef1) != null) {
                boolExpRef1 = boolStorage.get(expRef1);
            } else if (expRef1.equals("true") || expRef1.equals("false")) {
                boolExpRef1 = Boolean.parseBoolean(expRef1);
            } else {
                intExpRef1 = Integer.parseInt(expRef1);
                ref1IsInt = true;
            }

            if (ref1IsInt) {
                switch (uop) {
                    case "#":
                        return String.valueOf((intExpRef1));
                    case "~":
                        return String.valueOf((-intExpRef1));
                }
            } else {
                switch (uop) {
                    case "!":
                        return String.valueOf((!boolExpRef1));
                }
            }
            return "";
        }
    }

    public class skip extends Statement {
        public skip(String label) {
            super.label = label;
        }

        public String getLabel() {
            return super.label;
        }

        @Override
        public void runState() {
            // do nothing
        }
    }

    public class assign extends Statement {
        private String varName;
        private String expRef;

        public assign(String label, String varName, String expRef) {
            super.label = label;
            this.varName = varName;
            this.expRef = expRef;
        }

        @Override
        public void runState() {
            if (intStorage.get(varName) != null) {
                if (expStorage.get(expRef) != null) {
                    String newVar = expStorage.get(expRef).calculate();
                    if (newVar.equals("true") || newVar.equals("false")) {
                        boolStorage.put(varName, Boolean.parseBoolean(newVar));
                    } else
                        intStorage.put(varName, Integer.parseInt(newVar));
                } else if (expRef.equals("true")) {
                    boolStorage.put(varName, true);
                } else if (expRef.equals("false")) {
                    boolStorage.put(varName, false);
                } else {
                    intStorage.put(varName, Integer.parseInt(expRef));
                }
            }
        }
    }

}
