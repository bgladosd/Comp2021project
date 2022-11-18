package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class Memory {
    private HashMap<String, DataObject> dataMemory;
    private HashMap<String, Command> cmdMemory;
    private HashMap<String, Command> programMemory;

    private Queue<Command> runnedCommand;

    private String runningProgramName;
    private ArrayList<String> breakpointList;
    private ArrayList<String> instrumentList;

    private boolean executing = false;
    private boolean debugMode = false;

    String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print",
            "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect" };

    public Memory() {
        dataMemory = new HashMap<>();
        cmdMemory = new HashMap<>();
        programMemory = new HashMap<>();
        runnedCommand = new ArrayDeque<>();

        runningProgramName = null;
        breakpointList = new ArrayList<>();
        instrumentList = new ArrayList<>();
    }

    public boolean getDebugMode() {
        return this.debugMode;
    }

    public void setDebugMode(boolean b) {
        this.debugMode = b;
    }

    public String getRunningProgramName() {
        return runningProgramName;
    }

    public void setRunningProgramName(String s) {
        this.runningProgramName = s;
    }

    public boolean toggleBreakPointInList(String s) {
        if (breakpointList.contains(s)) {
            breakpointList.remove(s);
            return false;
        }
        breakpointList.add(s);
        return true;
    }

    public void addInstrument(String s) {
        instrumentList.add(s);
    }

    public void reSetVariableData() {
        dataMemory.values().removeIf(i -> !i.type.equals("e"));
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    public boolean getExecuting() {
        return this.executing;
    }

    public Queue<Command> getRunnedCommand() {
        return runnedCommand;
    }

    // things to check before command executed by commanExecute
    public void preExecution(Command cmd) {
        // store executed command to runned command list
        if (!runnedCommand.contains(cmd)) {
            runnedCommand.add(cmd);
        }

        // check if this command need to break point
        if (getDebugMode()) {
            for (int i=breakpointList.size()-1;i>=0;i--) {

                String[] sp = breakpointList.get(i).split(" ");
                if (!sp[0].equals(getRunningProgramName())) {
                    continue;
                }
                if (!sp[1].equals(cmd.getLabel())) {
                    continue;
                }
                // break program
                System.out
                        .println("Entered debug mode, stoped before : " + breakpointList.get(i) + " input 'quit' to exit debug mode");
                while (true) {

                    Scanner scanner1 = new Scanner(System.in); // Create a Scanner object
                    System.out.print(">");
                    String input = scanner1.nextLine(); // Read user input
                    String[] ss = input.split(" ");
                    if (input.equals("debug " + sp[0])) {
                        break;
                    } else if (ss[0].equals("inspect")) {
                        new CommandInspect(ss, this);
                    } else if (ss[0].equals("togglebreakpoint")) {
                        new CommandToggleBreakPoint(ss, this);
                    } else if (ss[0].equals("quit")) {
                        setDebugMode(false);
                        break;
                    } else {
                        System.out.println(
                                "please end debug mode before enter other command by with :" + "debug " + sp[0]);
                    }

                }
            }
        }
        // check if it need instrument
        for (String string : instrumentList) {
            String[] sp = string.split(" ");
            if (!sp[0].equals(getRunningProgramName())) {
                continue;
            }
            if (!sp[1].equals(cmd.getLabel())) {
                continue;
            }
            if (sp[2].equals("before")) {
                DataObject dob = new DataObject();
                if (!dob.autoSetData(sp[3], this)) {
                    System.out.println("instructment can't find the variable with var name : " + sp[3]);
                    continue;
                }
                System.out.println("{" + dob + "}");
            }
        }

    }

    // things to check after command executed by commandExecute
    public void postExecution(Command cmd) {

        for (String string : instrumentList) {
            String[] sp = string.split(" ");
            if (!sp[0].equals(getRunningProgramName())) {
                continue;
            }
            if (!sp[1].equals(cmd.getLabel())) {
                continue;
            }
            if (sp[2].equals("after")) {
                DataObject dob = new DataObject();
                if (!dob.autoSetData(sp[3], this)) {
                    System.out.println("instructment can't find the variable with var name : " + sp[3]);
                    continue;
                }
                System.out.println("{" + dob + "}");
            }
        }
    }

    public void resetRunnedCommand() {
        runnedCommand = new ArrayDeque<>();
    }

    public void addData(String dataName, DataObject data) {
        dataMemory.put(dataName, data);
    }

    public DataObject getData(String dataName) {
        return dataMemory.get(dataName);
    }

    public void addCmd(String label, Command cmd) {
        cmdMemory.put(label, cmd);
    }

    public Command getCmd(String label) {
        return cmdMemory.get(label);
    }

    public Command getProgram(String programName) {
        return programMemory.get(programName);
    }

    public void addProgram(String programName, Command program) {
        programMemory.put(programName, program);
    }

    public void printData() {
        for (String data : dataMemory.keySet()) {
            System.out.println(data + " : " + getData(data));
        }
    }

    public void printCmd() {
        for (String data : cmdMemory.keySet()) {
            System.out.println(data + " : " + getCmd(data));
        }

    }

    public Boolean checkIsValidExpression(String s) {
        DataObject dob = new DataObject();
        if (!dob.autoSetData(s, this)) {
            if (!checkIsValidNameOrLabel(s)) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    public boolean checkIsValidNameOrLabel(String s) {
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

        for (String string : Identifiers) {
            if (s.equals(string)) {
                return false;
            }
        }

        return true;

    }

    public boolean checkIsValidProgramName(String s) {
        // ascii table [(48)0-9(58),(65)A-Z(90),(97)a-z(122)]
        // case1 if more than eight characters
        if (s.length() > 40) {
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
        for (String string : Identifiers) {
            if (s.equals(string)) {
                return false;
            }
        }

        return true;

    }
}
