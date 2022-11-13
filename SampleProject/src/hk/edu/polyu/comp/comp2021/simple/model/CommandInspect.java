package hk.edu.polyu.comp.comp2021.simple.model;

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
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! inspect statement should only have 3 elements which is (inspect programName varName)");
            return;
        }
        programName = cmd[1];
        varName = cmd[2];

        inspectObject = new DataObject();

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

}