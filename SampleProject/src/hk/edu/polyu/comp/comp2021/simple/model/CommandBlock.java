package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.ArrayList;
/** block command. this command store commands
 * @author Jack Lee
*/
public class CommandBlock implements Command {
    private String cmdString;
    private String label;
    private ArrayList<String> cmdListString = new ArrayList<String>();
    private ArrayList<Command> cmdList = new ArrayList<Command>();

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        cmdList = new ArrayList<Command>();
        for (String command : cmdListString) {
            if (m.getCmd(command) == null) {
                System.out.println("Statement : " + command + " can not be found ");
                return new DataObject("false", m);
            }
            cmdList.add(m.getCmd(command));
        }
        for (Command command : cmdList) {
            command.execute(m);
        }
        m.postExecution(this);
        return null;

    }

    /** check is the command valid and add to command list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandBlock(String[] cmd, Memory m) {
        if (cmd.length < 3) {
            System.out.println(
                    "instruction failed! block statement should have at least 3 elements which in format of (block lab statementLab1 ... statementLabn)");
            return;
        }
        String label = cmd[1];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }

        for (int i = 2; i < cmd.length; i++) {
            if (!m.checkIsValidNameOrLabel(cmd[i])) {
                System.out.println(
                        cmd[i] + " is not a valid statement label name");
                return;
            } else
                cmdListString.add(cmd[i]);
        }

        setLabel(label);
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
        this.cmdString = s;
    }

}