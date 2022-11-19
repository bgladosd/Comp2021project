package hk.edu.polyu.comp.comp2021.simple.model;
/** execute command. this command execute the program
 * @author Jack Lee
 */
public class CommandExecute implements Command {

    private String label;

    private  String program;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getProgram(program);
        if (c == null) {
            System.out.println("Program : " + program + " can not be found ");
            return null;
        }
        m.reSetVariableData();
        m.setRunningProgramName(program);
        m.resetRunnedCommand();
        m.setExecuting(true);
        c.execute(m);
        m.setExecuting(false);
        m.setRunningProgramName(null);
        return null;

    }

    public CommandExecute(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! execute statement should only have 2 elements which are (execute program1)");
            return;
        }
        program = cmd[1];

        this.execute(m);

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
        return "";
    }

    @Override
    public void setCmdString(String s) {

    }

}
