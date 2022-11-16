package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandUnexpr implements Command {
    String label;
    String dataName;
    DataObject dataObject;

    String expName;
    String expRef1;
    String uop;

    boolean executedToData=false;

    @Override
    public DataObject execute(Memory m) {
        // if (!executedToData) {
        //     m.addData(expName, new DataObject(this, m));
        // }
        DataObject expRefObject = new DataObject();
        if (expRefObject.autoSetData(expRef1, m)) {
        } else {
            System.out.println(
                    "ExpRef1 is not a valid data");
            return new DataObject("false", m);
        }
        

        if (expRefObject.type == "int") {
            Integer intExpRef1 = (Integer) expRefObject.o;
            switch (uop) {
                case "#":
                    dataObject = new DataObject(String.valueOf((intExpRef1)), m);
                    break;
                case "~":
                    dataObject = new DataObject(String.valueOf((-intExpRef1)), m);
                    break;
            }
        } else if(expRefObject.type == "bool") {
            Boolean boolExpRef1 = (Boolean) expRefObject.o;
            switch (uop) {
                case "!":
                    dataObject = new DataObject(String.valueOf((!boolExpRef1)), m) ;
            }
        }
        return dataObject;
    }

    public CommandUnexpr(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! unnexpr statement should only have 4 elements which is (unexpr expName uop expRef1)");
            return;
        }
        expName = cmd[1];
        uop = cmd[2];
        expRef1 = cmd[3];

        if (!m.checkIsValidNameOrLabel(expName)) {
            System.out.println(
                    expName + " is not a valid expression name");
            return;
        }
        // delete later, updated check with checkIsValidExpression below
        // DataObject expRefObject1 = new DataObject();

        // if (expRefObject1.autoSetData(expRef1, m)) {
        // } else {
        //     System.out.println(
        //             "ExpRef1 is not a valid data");
        //     return;
        // }
        if (!m.checkIsValidExpression(expRef1)) {
            System.out.println(
                    "ExpRef1 is not a valid data");
            return;
        }

        executedToData=false;
        m.addCmd(expName, this);
        m.addData(expName, new DataObject(this, m));
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
