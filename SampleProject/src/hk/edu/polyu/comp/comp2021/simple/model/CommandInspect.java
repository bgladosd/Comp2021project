package hk.edu.polyu.comp.comp2021.simple.model;


// running in debug mode
public class CommandInspect implements Command {
    String label;
    String programName;
    String varName;
    DataObject inspectObject;

    @Override
    public DataObject execute(Memory m) {

        
        if (inspectObject.autoSetData(varName, m)) {
        } else {
            System.out.println(
                    "can't find varName");
            return null;
        }
        System.out.println(inspectObject);
        return inspectObject;
    }

    public CommandInspect(String[] cmd, Memory m) {
        //just for debuging delete later
        if (cmd[1].equals("all")) {
            m.printData();
            return;
        }
        if (cmd[1].equals("allf")) {
            m.printCmd();
            return;
        }
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! inspect statement should only have 3 elements which is (inspect programName varName)");
            return;
        }
        programName = cmd[1];
        varName = cmd[2];

        inspectObject = new DataObject();
        this.execute(m);

        // if (inspectObject.autoSetData(varName,m)) {
        // }else{
        // System.out.println(
        // "can't find varName");
        // return;
        // }

        // command check tegrity end

    }

    @Override
    public void setLabel(String l) {
        this.label = l;

    }

    @Override
    public String getLabel() {
        return label;
    }
    
    @Override
    public String getCmdString() {
        return "";
    }

    @Override
    public void setCmdString(String s) {
        
    }


}