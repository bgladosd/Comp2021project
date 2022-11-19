package hk.edu.polyu.comp.comp2021.simple.model;
/** vardef command. it creates a new variable when executed
 * @author Jack Lee
 */
public class CommandVarDef implements Command {
    private String label;
    private String varName;
    private DataObject dataObject=new DataObject();
    private String cmdString;
    private String type;
    private String expRef;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        if (!dataObject.autoSetData(expRef, m)) {
            System.out.println("instruction failed! expRef is not valid value");
            return null;
        }
        if (!dataObject.getType().equals(type)) {
            System.out.println("instruction failed! expRef is not type :" + type);
            return null;
        }
        m.addData(varName, dataObject);
        m.postExecution(this);
        return (null);
    }
    /** check is the command valid and add to command list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandVarDef(String[] cmd, Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! vardef statement should only have 5 elements which is (vardef lable type varName expRef)");
            return;
        }
        label = cmd[1];
        type = cmd[2];
        varName = cmd[3];
        expRef = cmd[4];
        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println("instruction failed! " + label + " is not a valid label name");
            return;
        }

        // command check tegrity end
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