package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandSkip implements Command {
    String label;
    String cmdString;
    @Override
    public DataObject execute(Memory m) {
        m.addRunnedCommand(this);
        return null;
    }

    public CommandSkip(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! skip statement should only have 2 elements which is (skip skip1)");
            return;
        }
        label = cmd[1];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
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
    
    @Override
    public String getCmdString() {
        return this.cmdString;
    }

    @Override
    public void setCmdString(String s) {
        this.cmdString=s;
    }

}
