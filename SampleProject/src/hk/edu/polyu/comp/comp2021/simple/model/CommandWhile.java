package hk.edu.polyu.comp.comp2021.simple.model;
/** while command. this command will keep executing a command when the giving expRef is true
 * @author Jack Lee
 */
public class CommandWhile implements Command {
    private String cmdString;
    private String label;
    private String expRef;
    private Boolean expBoolean;
    private String statementLab1;
    private Command statement;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        DataObject dataO = new DataObject();
        if (m.getCmd(statementLab1) == null) {
            System.out.println("execute " + label + " failed! statementLab1 can not be found");
            return null;
        }
        if (!dataO.autoSetData(expRef, m)) {
            System.out.println("execute " + label + " failed! unable to load expRef");
            return null;
        }
        if (!dataO.getType().equals("bool")) {
            System.out.println("execute " + label + " failed! expRef is not boolean");
            return null;
        }

        statement = m.getCmd(statementLab1);

        expBoolean = (boolean) dataO.getO();
        while (expBoolean) {
            statement.execute(m);
            if (!dataO.autoSetData(expRef, m)) {
                System.out.println("execute " + label + " failed! unable to load expRef");
                return null;
            }
            if (!dataO.getType().equals("bool")) {
                System.out.println("execute " + label + " failed! expRef is not boolean");
                return null;
            }
            expBoolean = (boolean) dataO.getO();
        }

        m.postExecution(this);
        return null;

    }

    public CommandWhile(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! if statement should only have 4 elements which is (while lab expRef statementLab1)");
            return;
        }
        String label = cmd[1];
        expRef = cmd[2];
        statementLab1 = cmd[3];

        // command check tegrity end
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
                    statementLab1 + " is not a valid statement label name");
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
