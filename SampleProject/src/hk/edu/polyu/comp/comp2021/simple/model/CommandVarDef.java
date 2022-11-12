package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.IOException;

public class CommandVarDef implements Command {
    String label;
    String dataName;
    DataObject dataObject;
    @Override
    public DataObject execute(Memory m)  {
        
        m.addData(dataName, dataObject);
        return(null);
    }
    public CommandVarDef(String label,String dataName,DataObject dataObject) {
        this.label=label;
        this.dataName=dataName;
        this.dataObject=dataObject;
    }
    @Override
    public void setLabel(String l) {
        this.label=l;
        
    }
    @Override
    public String getLabel() {
        return label;
    }

    

    
}