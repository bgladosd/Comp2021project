package hk.edu.polyu.comp.comp2021.simple.model;
/** skip command. it do nothing
 * @author kuri0606
 */
public class CommandSkip implements Command {
    private String label;
    private String cmdString;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        m.postExecution(this);
        return null;
    }
    /** check is the command valid and add to command list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandSkip(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! skip statement should only have 2 elements which is (skip skip1)");
            return;
        }
        String label = cmd[1];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
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

    @Override
    public String getCmdString() {
        return this.cmdString;
    }

    @Override
    public void setCmdString(String s) {
        this.cmdString = s;
    }

}
