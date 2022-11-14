package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandAssign implements Command {
    String label;
    String dataName;
    DataObject dataObject;

    String expRef;

    @Override
    public DataObject execute(Memory m) {
        m.addData(label, new DataObject(this, m));
        DataObject dataObject1 = new DataObject();
        DataObject dataObject2 = new DataObject();
        String varName = dataName;
        if (dataObject1.autoSetData(varName, m)) {
        } else {
            System.out.println(
                    "Error on :" + label + " varName is not exists");
            return new DataObject("false", m);
        }
        if (dataObject2.autoSetData(expRef, m)) {
        } else {
            System.out.println(
                    "Error on :" + label + " ExpRef is not a valid data");
            return new DataObject("false", m);
        }
        // can't do calculation if they are not the same type
        if (!dataObject1.type.equals(dataObject2.type)) {
            System.out.println(
                    "Error on :" + label + " can't assign if varName and ExpRef are not the same type");
            return new DataObject("false", m);
        }

        // replace old
        if (dataObject1.type == "int") {
            Integer intRef = (Integer) dataObject2.o;
            m.addData(dataName, new DataObject(String.valueOf(intRef), m));
        } else if (dataObject1.type == "bool") {
            Boolean boolRef = (Boolean) dataObject2.o;
            m.addData(dataName, new DataObject(String.valueOf(boolRef), m));
        }

        return null;
    }

    public CommandAssign(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! assign statement should only have 4 elements which is (assign lab varName expRef)");
            return;
        }
        label = cmd[1];
        String varName = cmd[2];
        expRef = cmd[3];

        if (!Simple.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }

        DataObject dataObject1 = new DataObject();
        DataObject dataObject2 = new DataObject();


        if (!dataObject1.autoSetData(varName, m)) {
            System.out.println("instruction failed! variable: "+varName+" is not exists");
            return;
        }

        if (!dataObject2.autoSetData(expRef, m)) {
            System.out.println("instruction failed! expRef is not valid value");
            return;
        }

        if (!dataObject1.type.equals(dataObject2.type)) {
            System.out.println("instruction failed! Not the same type! "+varName+" is :" + dataObject1.type + " "+expRef+"is :"+dataObject2.type);
            return;
        }


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

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }
}
