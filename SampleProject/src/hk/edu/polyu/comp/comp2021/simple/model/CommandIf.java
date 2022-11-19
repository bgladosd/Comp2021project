package hk.edu.polyu.comp.comp2021.simple.model;
/** if command. this command check expref is true of false to execute label1 and label2
 * @author Jack Lee
 */
public class CommandIf implements Command {
    private String cmdString;
    private String label;
    private String expRef;
    private Boolean expBoolean;
    private String statementLab1;
    private String statementLab2;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        DataObject dataO = new DataObject();
        if (!dataO.autoSetData(expRef, m)) {
            System.out.println("execute " + label + " failed! unable to load expRef");
            return null;
        }
        if (!dataO.getType().equals("bool")) {
            System.out.println("execute " + label + " failed! expRef is not boolean");
            return null;
        }
        expBoolean = (boolean) dataO.getO();
        if (expBoolean) {
            if (m.getCmd(statementLab1) == null) {
                System.out.println("execute " + label + " failed! statementLab1 can not be find");
                return null;
            }
            m.getCmd(statementLab1).execute(m);
        } else {
            if (m.getCmd(statementLab2) == null) {
                System.out.println("execute " + label + " failed! statementLab2 can not be find");
                return null;
            }
            m.getCmd(statementLab2).execute(m);
        }
        m.postExecution(this);
        return null;

    }

    public CommandIf(String[] cmd, Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! if statement should only have 5 elements which is (if lab expRef statementLab1 statementLab2)");
            return;
        }
        String label = cmd[1];
        expRef = cmd[2];
        statementLab1 = cmd[3];
        statementLab2 = cmd[4];

        if (!m.checkIsValidNameOrLabel(label)) {
            System.out.println(
                    label + " is not a valid label name");
            return;
        }
        if (!m.checkIsValidExpression(expRef)) {
            System.out.println(
                    expRef + " is not a valid expression");
            return;
        }
        if (!m.checkIsValidNameOrLabel(statementLab1)) {
            System.out.println(
                    statementLab1 + " is not a valid label name");
            return;
        }
        if (!m.checkIsValidNameOrLabel(statementLab2)) {
            System.out.println(
                    statementLab2 + " is not a valid label name");
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