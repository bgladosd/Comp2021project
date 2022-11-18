package hk.edu.polyu.comp.comp2021.simple.model;


// running in debug mode
public class CommandToggleBreakPoint implements Command {
    String label;
    String programName;
    String breakPointLabel;


    @Override
    public DataObject execute(Memory m) {
        String breakPointString=programName+" "+breakPointLabel;
        if ( m.toggleBreakPointInList(breakPointString)) {
            System.out.println("break point : "+breakPointString + " added");
        }else{
            System.out.println("break point : "+breakPointString + " removed");
        }
        return null;
    }

    public CommandToggleBreakPoint(String[] cmd, Memory m) {
        //just for debuging delete later

        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! togglebreakpoint statement should only have 3 elements which is (togglebreakpoint programName statementLab)");
            return;
        
        }
        label=cmd[0];
        programName = cmd[1];
        breakPointLabel = cmd[2];
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