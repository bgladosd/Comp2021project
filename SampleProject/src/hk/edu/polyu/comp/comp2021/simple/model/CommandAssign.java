package hk.edu.polyu.comp.comp2021.simple.model;
/** assign command. this command assign a expRef to an existing variable
 * @author kuri0606
 */
public class CommandAssign implements Command {
    private String cmdString;
    private String label;
    private String varName;
    private String expRef;

    @Override
    public DataObject execute(Memory m) {

        m.preExecution(this);

       
        DataObject dataObject1 = m.getData(varName);
        DataObject dataObject2 = new DataObject();

        if (m.getData(varName) == null) {
            System.out.println(
                    "Error on :" + label + " " + varName + " not found");
            return new DataObject("false", m);
        }

        if (dataObject2.autoSetData(expRef, m)) {
        } else {
            System.out.println(
                    "Error on :" + label + " ExpRef is not a valid data");
            return new DataObject("false", m);
        }

        if (!dataObject1.getType().equals(dataObject2.getType())) {
            System.out.println("Error on :" + label + " can't assign if " + varName + " and " + expRef
                    + " are not the same data type");
            return new DataObject("false", m);
        }

        m.addData(varName, dataObject2);
        m.postExecution(this);
        return new DataObject("false", m);
    }

    /** check is the command valid and add to command list
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandAssign(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! assign statement should only have 4 elements which is (assign lab varName expRef)");
            return;
        }
        label = cmd[1];
        varName = cmd[2];
        expRef = cmd[3];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }
        if (!m.checkIsValidNameOrLabel(varName)) {
            System.out.println(
                    varName + " is not a valid variable name");
            return;
        }
        if (!m.checkIsValidExpression(expRef)) {
            System.out.println(
                    expRef + " is not a valid Expression");
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
