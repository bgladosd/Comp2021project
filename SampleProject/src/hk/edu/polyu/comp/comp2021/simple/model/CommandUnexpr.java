package hk.edu.polyu.comp.comp2021.simple.model;
/** unexpr command. this command add a unary expression to data
 * @author kuri0606
 */
public class CommandUnexpr implements Command {
    private String label;
    private DataObject dataObject;
    private String cmdString;
    private String expName;
    private String expRef1;
    private String uop;
    private String[] bopSuitArray = { "~", "!", "#", };

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        if (!m.getExecuting()) {
            return new DataObject("false", m);
        }
        DataObject expRefObject = new DataObject();
        if (expRefObject.autoSetData(expRef1, m)) {
        } else {
            System.out.println(
                    "Error on :" + expName + " ExpRef1 is not a valid data");
            return new DataObject("false", m);
        }

        if (expRefObject.getType() .equals("int") ) {
            Integer intExpRef1 = (Integer) expRefObject.getO();
            switch (uop) {
                case "#":
                    dataObject = new DataObject(String.valueOf((intExpRef1)), m);
                    break;
                case "~":
                    dataObject = new DataObject(String.valueOf((-intExpRef1)), m);
                    break;
            }
        } else if (expRefObject.getType() .equals("bool") ) {
            Boolean boolExpRef1 = (Boolean) expRefObject.getO();
            switch (uop) {
                case "!":
                    dataObject = new DataObject(String.valueOf((!boolExpRef1)), m);
            }
        }
        m.postExecution(this);
        return dataObject;
    }
    /** check is the command valid and add ExpRef
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandUnexpr(String[] cmd, Memory m) {
        if (cmd.length != 4) {
            System.out.println(
                    "instruction failed! unexpr statement should only have 4 elements which is (unexpr expName uop expRef1)");
            return;
        }
        expName = cmd[1];
        uop = cmd[2];
        expRef1 = cmd[3];

        if (!m.checkIsValidNameOrLabel(expName)) {
            System.out.println(
                    expName + " is not a valid expression name");
            return;
        }
        if (!m.checkIsValidExpression(expRef1)) {
            System.out.println(
                    expRef1 + " is not a valid expression");
            return;
        }
        boolean goodBop = false;
        for (String string : bopSuitArray) {
            if (uop.equals(string)) {
                goodBop = true;
                break;
            }
        }
        if (!goodBop) {
            System.out.println(
                    uop + " is not a valid uop");
            return;
        }

        // command check end
        setLabel(null);

        m.addData(expName, new DataObject(this, m));
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
