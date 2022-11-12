package hk.edu.polyu.comp.comp2021.simple.model;
import java.util.HashMap;

public class Memory {
    private HashMap<String,DataObject> dataMemory;

    
    public Memory(){
        dataMemory = new HashMap<>();

    }
    public void addData(String dataName,DataObject data){
        dataMemory.put(dataName, data);
    }
    public DataObject getData(String dataName){
        return dataMemory.get(dataName);
    }
}
