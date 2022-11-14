package hk.edu.polyu.comp.comp2021.simple.model;
import java.util.HashMap;

public class Memory {
    private HashMap<String,DataObject> dataMemory;
    private HashMap<String,Command> cmdMemory;

    
    public Memory(){
        dataMemory = new HashMap<>();
        cmdMemory= new HashMap<>();

    }
    public void addData(String dataName,DataObject data){
        dataMemory.put(dataName, data);
    }
    public DataObject getData(String dataName){
        return dataMemory.get(dataName);
    }
    public void addCmd(String label,Command cmd){
        cmdMemory.put(label, cmd);
    }
    public Command getCmd(String label){
        return cmdMemory.get(label);
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
}
