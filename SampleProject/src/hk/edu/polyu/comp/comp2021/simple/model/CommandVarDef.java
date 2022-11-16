package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandVarDef implements Command {
    String label;
    String dataName;
    DataObject dataObject;
    String cmdString;
    String type;
    String expRef;

    @Override
    public DataObject execute(Memory m) {
        if (!dataObject.autoSetData(expRef, m)) {
            System.out.println("instruction failed! expRef is not valid value");
            return null;
        }
        if (!dataObject.type.equals(type)) {
            System.out.println("instruction failed! expRef is not type :" + type);
            return null;
        }
        m.addData(dataName, dataObject);

        return (null);
    }

    public CommandVarDef(String[] cmd, Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! vardef statement should only have 5 elements which is (vardef lable type varName expRef)");
            return;
        }
        String label = cmd[1];
        type = cmd[2];
        String varName = cmd[3];
        expRef = cmd[4];
        // if (checkValidName(cmd[1])) {

        // }

        // check label exist

        // check variable exist

        // should have more check
        // command check tegrity end
        DataObject dataObject = new DataObject();
        if (!dataObject.autoSetData(expRef, m)) {
            System.out.println("instruction failed! expRef is not valid value");
            return;
        }
        if (!dataObject.type.equals(type)) {
            System.out.println("instruction failed! expRef is not type :" + type);
            return;
        }


        setLabel(label);
        setDataObject(dataObject);
        setDataName(varName);

        m.addCmd(label, this);

        // for testing
        // m.getCmd(label).execute(m);
        

    }

    @Override
    public void setLabel(String l) {
        this.label = l;

    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

}