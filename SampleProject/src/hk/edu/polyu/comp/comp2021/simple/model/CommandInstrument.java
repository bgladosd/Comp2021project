package hk.edu.polyu.comp.comp2021.simple.model;


// running in debug mode
public class CommandInstrument implements Command {
    String label;
    String programName;
    String instrumentLabel;
    String pos;
    String expRef;


    @Override
    public DataObject execute(Memory m) {
        String instrumentString=programName+" "+instrumentLabel+" "+pos+" "+expRef;
        m.addInstrument(instrumentString);
        System.out.println("Instructment : "+ instrumentString + " added");
        return null;
    }

    public CommandInstrument(String[] cmd, Memory m) {
        //just for debuging delete later

        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! instrument statement should only have 5 elements which is (instrument programName statementLab pos expRef)");
            return;
        
        }
        label=cmd[0];
        programName = cmd[1];
        instrumentLabel = cmd[2];
        pos=cmd[3];
        expRef=cmd[4];

        // if (inspectObject.autoSetData(varName,m)) {
        // }else{
        // System.out.println(
        // "can't find varName");
        // return;
        // }

        // command check tegrity end
        this.execute(m);

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