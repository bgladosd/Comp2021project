package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandPrint implements Command {
    String label;
    String expRef;

    @Override
    public DataObject execute(Memory m) {
        DataObject dataObject = new DataObject();
        if (dataObject.autoSetData(expRef, m)) {
        } else {
            System.out.println(
                    "ExpRef is not a valid data");
            return new DataObject("false", m);
        }

        System.out.println("["+dataObject+"]");
        
        return null;

    }

    public CommandPrint(String[] cmd, Memory m) {
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! print statement should only have 3 elements which is (print lab expRef)");
            return;
        }
        label = cmd[1];
        expRef = cmd[2];

        if (!Simple.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }

        DataObject dataObject = new DataObject();
        if (!dataObject.autoSetData(expRef, m)) {
            System.out.println("instruction failed! expRef is not valid value");
            return;
        }

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
}
