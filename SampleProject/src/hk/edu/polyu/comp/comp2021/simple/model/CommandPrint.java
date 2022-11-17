package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandPrint implements Command {
    String label;
    String expRef;
    String cmdString;
    @Override
    public DataObject execute(Memory m) {
        m.addRunnedCommand(this);
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
        String label = cmd[1];
        expRef = cmd[2];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }

        if (!m.checkIsValidExpression(expRef)) {
            System.out.println(
                    expRef + " is not valid expression");
            return;
        }

        setLabel(label);
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
