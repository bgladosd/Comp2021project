package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandAssign implements Command {
    String cmdString;
    String label;
    String dataName;
    DataObject dataObject;

    String expRef;

    @Override
    public DataObject execute(Memory m) {

        m.preExecution(this);

        String varName = dataName;
        DataObject dataObject1 = m.getData(varName);
        DataObject dataObject2 = new DataObject();

        if (m.getData(varName) == null) {
            System.out.println(
                    "Error on :" + label + " " + varName + " not found");
            return new DataObject("false", m);
        }

        if (dataObject2.autoSetData(expRef, m)) {
        } else {
            System.out.println(
                    "Error on :" + label + " ExpRef is not a valid data");
            return new DataObject("false", m);
        }

        if (!dataObject1.type.equals(dataObject2.type)) {
            System.out.println("Error on :" + label + " can't assign if " + varName + " and " + expRef
                    + " are not the same data type");
            return new DataObject("false", m);
        }

        m.addData(dataName, dataObject2);
        m.postExecution();
        return new DataObject("false", m);
    }

    public CommandAssign(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! assign statement should only have 4 elements which is (assign lab varName expRef)");
            return;
        }
        String label = cmd[1];
        String varName = cmd[2];
        expRef = cmd[3];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }
        if (!m.checkIsValidNameOrLabel(varName)) {
            System.out.println(
                    varName + " is not a valid variable name");
            return;
        }
        if (!m.checkIsValidExpression(expRef)) {
            System.out.println(
                    expRef + " is not a valid Expression");
            return;
        }

        // updated checking delete later
        // DataObject dataObject1 = new DataObject();
        // DataObject dataObject2 = new DataObject();

        // if (!dataObject1.autoSetData(varName, m)) {
        // System.out.println("instruction failed! variable: "+varName+" is not
        // exists");
        // return;
        // }

        // if (!dataObject2.autoSetData(expRef, m)) {
        // System.out.println("instruction failed! expRef is not valid value");
        // return;
        // }

        // if (!dataObject1.type.equals(dataObject2.type)) {
        // System.out.println("instruction failed! Not the same type! "+varName+" is :"
        // + dataObject1.type + " "+expRef+"is :"+dataObject2.type);
        // return;
        // }

        setLabel(label);
        setDataName(varName);
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
        this.cmdString = s;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

}
