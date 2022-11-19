package hk.edu.polyu.comp.comp2021.simple.model;
/** togglebreakpoint command. this command toggle a breakpoint to make command break before running in debug mode
 * @author Jack Lee
 */
// running in debug mode
public class CommandToggleBreakPoint implements Command {
    private String label;
    private String programName;
    private String breakPointLabel;

    @Override
    public DataObject execute(Memory m) {
        String breakPointString = programName + " " + breakPointLabel;
        if (m.toggleBreakPointInList(breakPointString)) {
            System.out.println("break point : " + breakPointString + " added");
        } else {
            System.out.println("break point : " + breakPointString + " removed");
        }
        return null;
    }
    /** check is the command valid and add Breakpoint to breakpoint list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandToggleBreakPoint(String[] cmd, Memory m) {
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! togglebreakpoint statement should only have 3 elements which is (togglebreakpoint programName statementLab)");
            return;

        }
        label = cmd[0];
        programName = cmd[1];
        breakPointLabel = cmd[2];

        if (m.getProgram(programName) == null) {
            System.out.println(
                    programName + " : no such program");
            return;
        }
        if (m.getCmd(breakPointLabel) == null) {
            System.out.println(
                breakPointLabel + " : no such command is inside memory with this label");
            return;
        }
        // command check tegrity end
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