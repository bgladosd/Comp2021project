package hk.edu.polyu.comp.comp2021.simple.model;
/** instrument command. this command add a instrument, when program running and a command is executed that is instrumented, it will print out the expRef
 * @author Jack Lee
 */
public class CommandInstrument implements Command {
    private String label;
    private String programName;
    private String instrumentLabel;
    private String pos;
    private String expRef;

    @Override
    public DataObject execute(Memory m) {
        String instrumentString = programName + " " + instrumentLabel + " " + pos + " " + expRef;
        m.addInstrument(instrumentString);
        System.out.println("Instructment : " + instrumentString + " added");
        return null;
    }
    /** check is the command valid and add to instrument to instrument list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandInstrument(String[] cmd, Memory m) {
        // just for debuging delete later

        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! instrument statement should only have 5 elements which is (instrument programName statementLab pos expRef)");
            return;

        }
        label = cmd[0];
        programName = cmd[1];
        instrumentLabel = cmd[2];
        pos = cmd[3];
        expRef = cmd[4];

        if (!m.checkIsValidProgramName(programName)) {
            System.out.println(
                    programName + " is not a valid program Name");
            return;
        }
        if (m.getProgram(programName) == null) {
            System.out.println(
                    programName + " : no such program");
            return;
        }
        if (!m.checkIsValidNameOrLabel(instrumentLabel)) {
            System.out.println(
                    instrumentLabel + " is not a valid label");
            return;
        }
        if (m.getCmd(instrumentLabel) == null) {
            System.out.println(
                    instrumentLabel + " : no such command is inside memory with this label");
            return;
        }
        if (!m.checkIsValidExpression(expRef)) {
            System.out.println(
                    expRef + " is not a valid expression");
            return;
        }
        if (!(pos.equals("before") || pos.equals("after"))) {
            System.out.println(
                    "pos should only be 'before' or 'after'");
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