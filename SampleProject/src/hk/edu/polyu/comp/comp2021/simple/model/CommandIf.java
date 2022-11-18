package hk.edu.polyu.comp.comp2021.simple.model;


public class CommandIf implements Command {
    String cmdString;
    String label;
    String expRef;
    Boolean expBoolean;
    String statementLab1;
    String statementLab2;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        DataObject dataO = new DataObject();
        if (!dataO.autoSetData(expRef, m)) {
            System.out.println("execute " + label + " failed! unable to load expRef");
            return null;
        }
        if (!dataO.type.equals("bool")) {
            System.out.println("execute " + label + " failed!    expRef is not boolean");
            return null;
        }
        expBoolean = (boolean) dataO.o;
        if (expBoolean) {
            if (m.getCmd(statementLab1) == null) {
                System.out.println("execute " + label + " failed! statementLab1 can not be find");
                return null;
            }
            m.getCmd(statementLab1).execute(m);
        } else {
            if (m.getCmd(statementLab2) == null) {
                System.out.println("execute " + label + " failed! statementLab2 can not be find");
                return null;
            }
            m.getCmd(statementLab2).execute(m);
        }
        m.postExecution(this);
        return null;

    }

    public CommandIf(String[] cmd, Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! if statement should only have 5 elements which is (if lab expRef statementLab1 statementLab2)");
            return;
        }
        label = cmd[1];
        expRef = cmd[2];
        statementLab1 = cmd[3];
        statementLab2 = cmd[4];
        // command check tegrity end
        m.addCmd(label, this);
        

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
        return this.cmdString;
    }

    @Override
    public void setCmdString(String s) {
        this.cmdString=s;
    }


}