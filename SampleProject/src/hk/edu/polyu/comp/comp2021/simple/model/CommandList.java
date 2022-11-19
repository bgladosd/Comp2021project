package hk.edu.polyu.comp.comp2021.simple.model;

/** list command. this command list all the command that is executed in the program
 * @author Kk335577xyz
 */
public class CommandList implements Command {

    private String progName;
    private String label;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getProgram(progName);
        if (c == null) {
            System.out.println("Program : " + progName + " can not be found ");
            return null;
        }

        m.reSetVariableData();
        m.setRunningProgramName(null);
        m.resetRunnedCommand();
        m.setExecuting(true);
        c.execute(m);
        m.setExecuting(false);

        while (!m.getRunnedCommand().isEmpty()) {
            Command cs = m.getRunnedCommand().remove();
            System.out.println(cs.getCmdString());
        }

        return null;
    }
    /** check is the command valid and execute to show the command used in a program
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandList(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! list statement should only have 2 elements which is (list programName)");
            return;
        }

        this.progName = cmd[1];

        this.execute(m);
    }

    @Override
    public void setLabel(String l) {
        this.label = l;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getCmdString() {
        return null;
    }

    @Override
    public void setCmdString(String s) {

    }
}
