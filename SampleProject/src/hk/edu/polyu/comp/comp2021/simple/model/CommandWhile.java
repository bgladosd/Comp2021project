package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandWhile implements Command {
    String cmdString;
    String label;
    String expRef;
    Boolean expBoolean;
    String statementLab1;
    Command statement;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        DataObject dataO = new DataObject();
        if (m.getCmd(statementLab1) == null) {
            System.out.println("execute " + label + " failed! statementLab1 can not be find");
            return null;
        }
        if (!dataO.autoSetData(expRef, m)) {
            System.out.println("execute " + label + " failed! unable to load expRef");
            return null;
        }
        if (!dataO.type.equals("bool")) {
            System.out.println("execute " + label + " failed!    expRef is not boolean");
            return null;
        }

        statement=m.getCmd(statementLab1);

        expBoolean = (boolean) dataO.o;
        while (expBoolean) {
            statement.execute(m);
            if (!dataO.autoSetData(expRef, m)) {
                System.out.println("execute " + label + " failed! unable to load expRef");
                return null;
            }
            if (!dataO.type.equals("bool")) {
                System.out.println("execute " + label + " failed!    expRef is not boolean");
                return null;
            }
            expBoolean = (boolean) dataO.o;
        }

        m.postExecution();
        return null;

    }

    public CommandWhile(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! if statement should only have 5 elements which is (if lab expRef statementLab1 statementLab2)");
            return;
        }
        label = cmd[1];
        expRef = cmd[2];
        statementLab1 = cmd[3];
        
        //check expRef is bool or e

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
