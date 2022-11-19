package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

/**
 * memory class for SIMPLE, all data store here, including commands, data,
 * program, breakpoints, instruments, running states
 * 
 * @author Jack Lee
 */
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

    private String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print",
            "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect", "debug",
            "instrument", "togglebreakpoint" };

    /** constructor of memory, initialize all memory containers */
    public Memory() {
        dataMemory = new HashMap<>();
        cmdMemory = new HashMap<>();
        programMemory = new HashMap<>();
        runnedCommand = new ArrayDeque<>();
        runningProgramName = "";
        breakpointList = new ArrayList<>();
        instrumentList = new ArrayList<>();
    }

    /**
     * get the state of debug mode
     * 
     * @return bool state
     */
    public boolean getDebugMode() {
        return this.debugMode;
    }

    /**
     * set the debug state
     * 
     * @param b boolean
     */
    public void setDebugMode(boolean b) {
        this.debugMode = b;
    }

    /**
     * get the name of running program
     * 
     * @return String
     */
    public String getRunningProgramName() {
        return runningProgramName;
    }

    /**
     * set the name of running program
     * 
     * @param s name
     */
    public void setRunningProgramName(String s) {
        this.runningProgramName = s;
    }

    /**
     * @param s toggleBreakPoint command input string
     * @return boolean add or remove
     */
    public boolean toggleBreakPointInList(String s) {
        if (breakpointList.contains(s)) {
            breakpointList.remove(s);
            return false;
        }
        breakpointList.add(s);
        return true;
    }

    /**
     * add instrument
     * 
     * @param s instrument
     */
    public void addInstrument(String s) {
        instrumentList.add(s);
    }

    /** reset the variable data */
    public void reSetVariableData() {
        dataMemory.values().removeIf(i -> !i.getType().equals("e"));
    }

    /**
     * @param executing bool state
     */
    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    /**
     * @return boolean
     */
    public boolean getExecuting() {
        return this.executing;
    }

    /**
     * @return Queue<Command>
     */
    public Queue<Command> getRunnedCommand() {
        return runnedCommand;
    }

    /**
     * run at the start of when command is executed by execute command
     * 
     * @param cmd cmd
     */
    // things to check before command executed by commanExecute
    public void preExecution(Command cmd) {
        // store executed command to runned command list
        if (!runnedCommand.contains(cmd)) {
            runnedCommand.add(cmd);
        }

        // check if this command need to break point
        if (getDebugMode()) {
            for (int i = breakpointList.size() - 1; i >= 0; i--) {

                String[] sp = breakpointList.get(i).split(" ");
                if (!sp[0].equals(getRunningProgramName())) {
                    continue;
                }
                if (!sp[1].equals(cmd.getLabel())) {
                    continue;
                }
                // break program
                System.out
                        .println("Entered debug mode, stoped before : " + breakpointList.get(i)
                                + " input 'quit' to exit debug mode");
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

    /**
     * run after the command at the end of execution by execute command
     * 
     * @param cmd command
     */
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

    /** reset the runned command list */
    public void resetRunnedCommand() {
        runnedCommand = new ArrayDeque<>();
    }

    /**
     * put data inside data hashmap
     * 
     * @param dataName dataName
     * @param data     data object
     */
    public void addData(String dataName, DataObject data) {
        dataMemory.put(dataName, data);
    }

    /**
     * get data from hashmap with coressponding name
     * 
     * @param dataName dataname
     * @return DataObject
     */
    public DataObject getData(String dataName) {
        return dataMemory.get(dataName);
    }

    /**
     * add command to commandMemory
     * 
     * @param label label
     * @param cmd   cmd
     */
    public void addCmd(String label, Command cmd) {
        cmdMemory.put(label, cmd);
    }

    /**
     * get command with coressponding label
     * 
     * @param label label
     * @return Command
     */
    public Command getCmd(String label) {
        return cmdMemory.get(label);
    }

    /**
     * get program with coressponding name
     * 
     * @param programName program name
     * @return Command
     */
    public Command getProgram(String programName) {
        return programMemory.get(programName);
    }

    /**
     * get program command to programMemory
     * 
     * @param programName programName
     * @param program     program command
     */
    public void addProgram(String programName, Command program) {
        programMemory.put(programName, program);
    }

    /**
     * check string is valid expression
     * 
     * @param s expression string
     * @return Boolean
     */
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

    /**
     * check string is valid name or label
     * 
     * @param s name or label string
     * @return boolean
     */
    public boolean checkIsValidNameOrLabel(String s) {
        // ascii table [(48)0-9(58),(65)A-Z(90),(97)a-z(122)]
        // case1 if more than eight characters
        if (s.length() > 8) {
            return false;
        }
        // case2 if not english letters and digits
        for (char c : s.toCharArray()) {
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'b'))) {
                return false;
            }

        }

        // case3 if string start with 0-9
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
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

    /**
     * check string is valid programName
     * 
     * @param s program name
     * @return boolean
     */
    public boolean checkIsValidProgramName(String s) {
        // program name checking is not defined in description
        return true;

    }
}
