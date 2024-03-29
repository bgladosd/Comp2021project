package hk.edu.polyu.comp.comp2021.simple.model;
/** unexpr command. this command add a binary expression to data
 * @author kuri0606
 */
public class CommandBinexpr implements Command {
    private String cmdString;
    private String label;
    private DataObject dataObject;
    private String expName;
    private String expRef1;
    private String bop;
    private String expRef2;
    private String[] bopSuitArray = { "%", "+", "-", "*", "/", ">", "<", "<=", ">=", "==", "!=", "&&", "||" };

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        if (!m.getExecuting()) {
            return new DataObject("false", m);
        }
        DataObject expRefObject1 = new DataObject();
        DataObject expRefObject2 = new DataObject();
        if (expRefObject1.autoSetData(expRef1, m)) {
        } else {
            System.out.println(
                    "Error on :" + expName + " ExpRef1 is not a valid data");
            return new DataObject("false", m);
        }
        if (expRefObject2.autoSetData(expRef2, m)) {
        } else {
            System.out.println(
                    "Error on :" + expName + " ExpRef2 is not a valid data");
            return new DataObject("false", m);
        }
        // can't do calculation if they are not the same type
        if (!expRefObject1.getType().equals(expRefObject2.getType())) {
            System.out.println(
                    "Error on :" + expName + " can't do calculation if ExpRef1 and ExpRef2 are not the same type");
            return new DataObject("false", m);
        }

        if (expRefObject1.getType() .equals("int") ) {
            Integer intExpRef1 = (Integer) expRefObject1.getO();
            Integer intExpRef2 = (Integer) expRefObject2.getO();
            switch (bop) {
                case "%":
                    dataObject = new DataObject(String.valueOf((intExpRef1 % intExpRef2)), m);
                    break;
                case "+":
                    dataObject = new DataObject(String.valueOf((intExpRef1 + intExpRef2)), m);
                    break;
                case "-":
                    dataObject = new DataObject(String.valueOf((intExpRef1 - intExpRef2)), m);
                    break;
                case "*":
                    dataObject = new DataObject(String.valueOf((intExpRef1 * intExpRef2)), m);
                    break;
                case "/":
                    dataObject = new DataObject(String.valueOf((intExpRef1 / intExpRef2)), m);
                    break;
                case ">":
                    dataObject = new DataObject(String.valueOf((intExpRef1 > intExpRef2)), m);
                    break;
                case ">=":
                    dataObject = new DataObject(String.valueOf((intExpRef1 >= intExpRef2)), m);
                    break;
                case "<":
                    dataObject = new DataObject(String.valueOf((intExpRef1 < intExpRef2)), m);
                    break;
                case "<=":
                    dataObject = new DataObject(String.valueOf((intExpRef1 <= intExpRef2)), m);
                    break;
                case "==":
                    dataObject = new DataObject(String.valueOf((intExpRef1 == intExpRef2)), m);
                    break;
                case "!=":
                    dataObject = new DataObject(String.valueOf((intExpRef1 != intExpRef2)), m);
                    break;
            }
        } else if (expRefObject1.getType() .equals("bool") ) {
            Boolean boolExpRef1 = (Boolean) expRefObject1.getO();
            Boolean boolExpRef2 = (Boolean) expRefObject2.getO();
            switch (bop) {
                case "==":
                    dataObject = new DataObject(String.valueOf((boolExpRef1 == boolExpRef2)), m);
                    break;
                case "!=":
                    dataObject = new DataObject(String.valueOf((boolExpRef1 != boolExpRef2)), m);
                    break;
                case "||":
                    dataObject = new DataObject(String.valueOf((boolExpRef1 || boolExpRef2)), m);
                    break;
                case "&&":
                    dataObject = new DataObject(String.valueOf((boolExpRef1 && boolExpRef2)), m);
                    break;
            }
        }
        m.postExecution(this);
        return dataObject;

    }
    /** check is the command valid and add ExpRef
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandBinexpr(String[] cmd, Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! binexpr statement should only have 5 elements which is (binexpr expName expRef1 bop expRef2)");
            return;
        }
        expName = cmd[1];
        expRef1 = cmd[2];
        bop = cmd[3];
        expRef2 = cmd[4];

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
        if (!m.checkIsValidExpression(expRef2)) {
            System.out.println(
                    expRef2 + " is not a valid expression");
            return;
        }
        boolean goodBop = false;
        for (String string : bopSuitArray) {
            if (bop.equals(string)) {
                goodBop = true;
                break;
            }
        }
        if (!goodBop) {
            System.out.println(
                    bop + " is not a valid bop");
            return;
        }
        // command check tegrity end
        setLabel(label);
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