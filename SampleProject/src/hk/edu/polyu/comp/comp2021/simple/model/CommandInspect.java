package hk.edu.polyu.comp.comp2021.simple.model;

/** inspect command. this command inspect variable of varname and print it out in debug mode
 * @author Jack Lee
 */
// running in debug mode
public class CommandInspect implements Command {
    private String label;
    private String programName;
    private String varName;
    private DataObject inspectObject;

    @Override
    public DataObject execute(Memory m) {

        if (m.getData(varName) == null) {
            System.out.println("inspect can't find the variable with var name : " + varName);
            return null;
        }
        inspectObject = new DataObject(varName, m);
        System.out.println("<" + inspectObject + ">");
        return null;
    }

    public CommandInspect(String[] cmd, Memory m) {
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! inspect statement should only have 3 elements which is (inspect programName varName)");
            return;
        }
        programName = cmd[1];
        varName = cmd[2];
        if (!programName.equals(m.getRunningProgramName())) {
            System.out.println("program name is not correct it should be" + m.getRunningProgramName());
            return;
        }
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