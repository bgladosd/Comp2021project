package hk.edu.polyu.comp.comp2021.simple.model;
import java.util.HashMap;

public class Memory {
    private HashMap<String,DataObject> dataMemory;
    private HashMap<String,Command> cmdMemory;
    private HashMap<String,Command> programMemory;

    private boolean executing=false;
    
    public Memory(){
        dataMemory = new HashMap<>();
        cmdMemory= new HashMap<>();
        programMemory=new HashMap<>();

    }
    public void setExecuting(boolean executing) {
        this.executing = executing;
    }
    public boolean getExecuting(){
        return this.executing;
    }
    public void addData(String dataName,DataObject data){
        dataMemory.put(dataName, data);
    }
    public DataObject getData(String dataName){
        return dataMemory.get(dataName);
    }
    public void addCmd(String label,Command cmd){
        cmdMemory.put(label, cmd);
        System.out.println(label+" added");
    }
    public Command getCmd(String label){
        return cmdMemory.get(label);
    }
    public Command getProgram(String programName) {
        return programMemory.get(programName);
    }
    public void addProgram(String programName,Command program) {
        programMemory.put(programName, program);
    }
    public void printData(){
        for (String data : dataMemory.keySet()) {
            System.out.println(data + " : "+getData(data));
        }

    }
    public void printCmd(){
        for (String data : cmdMemory.keySet()) {
            System.out.println(data + " : "+getCmd(data));
        }

    }

    public Boolean checkIsValidExpression(String s) {
        DataObject dob= new DataObject();
        if (!dob.autoSetData(s, this)) {
            if (!checkIsValidNameOrLabel(s)) {
                return false;
            }
        }
        else{
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
        // please continue add
        String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect" };
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
        // please continue add
        String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect" };
        for (String string : Identifiers) {
            if (s.equals(string)) {
                return false;
            }
        }
        
        return true;

    }
}
