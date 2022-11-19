package hk.edu.polyu.comp.comp2021.simple.model;
/** a object that is used as a common dataType in our implementation of SIMPLE, it can auto setup as a suitable data with a string
 * @author Jack Lee
 */
public class DataObject {
    /** the lowest number of integer in SIMPLE*/
    public static final int intLowLimit = -99999;
    /** the largest number of integer in SIMPLE*/
    public static final int intUpLimit = 99999;
    private String type;
    private Object o;
    private Memory m;
    /** constructor of dataobject*/
    public DataObject() {

    }
    /** create data memory and call autoSetData to auto setup the DataObject
     * @param s string of data
     * @param m access to memory
     */
    public DataObject(String s, Memory m) {
        autoSetData(s, m);
    }
    /** set up the dataMemory with given parameters
     * @param type type of data
     * @param o object of data
     * @param m access to memory
     */
    public DataObject(String type, Object o, Memory m) {
        this.type = type;
        this.o = o;
        this.m = m;
    }
    /** set up the dataMemory with given parameters but this one is quick setup for command type
     * @param c command to save
     * @param m access to memory
     */
    public DataObject(Command c, Memory m) {
        this.type = "e";
        this.o = c;
        this.m = m;
    }

    /** basic get and set
     * @return String
     */
    public String getType() {
        return type;
    }
    
    
    /** basic get and set
     * @return Object
     */
    public Object getO() {
        return o;
    }

    
    /** basic get and set
     * @param o set object
     */
    public void setO(Object o) {
        this.o = o;
    }

    
    /** basic get and set
     * @param type set type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /** basic get and set
     * @param b set boolean
     */
    public void setBoolean(Boolean b) {
        o = b;
        type = "bool";
    }

    
    /** basic get and set
     * @param i set int
     */
    public void setInteger(Integer i) {
        o = i;
        type = "int";
    }

    
    /** auto set up the data to a corresponding data via string,
     * it will check if the string is inside data first to copy data as the finded variable or expression
     * if it is not it will also check if it is Integer or boolean
     * @param s string to detect corresponding object
     * @param m access to memory
     * @return boolean if the boolean is true it mean it set up the data successfully, if it is false it mean it fails to setup the data
     */
    public boolean autoSetData(String s, Memory m) {
        // is variable that inside memory
        this.m = m;
        if (m.getData(s) != null) {
            this.o = m.getData(s).getO();
            this.type = m.getData(s).getType();
            if (m.getData(s).getType().equals("e")) {
                if (!m.getExecuting()) {
                    return true;
                }

                while (this.type.equals("e")) {
                    Command tcc = (Command) this.o;
                    this.o = tcc.execute(m).getO();
                    this.type = tcc.execute(m).getType();
                }

            }
            return true;
            // is a boolean
        } else if (s.equals("true") || s.equals("false")) {
            this.type = "bool";
            this.o = Boolean.parseBoolean(s);
            return true;
            // is a integer
        } else if (isInteger(s)) {
            this.type = "int";
            int t = Integer.parseInt(s);
            if (t > intUpLimit) {
                t = intUpLimit;
            } else if (t < intLowLimit) {
                t = intLowLimit;
            }
            this.o = t;
            return true;
        }

        return false;
    }

    
    /** 
     * @param s
     * @return boolean
     */
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        if (type.equals("int")) {
            int t = (int) o;
            return String.valueOf(t);
        }
        if (type.equals("bool")) {
            Boolean t = (Boolean) o;
            return String.valueOf(t);
        }
        if (type.equals("e")) {
            Command t = (Command) o;
            return String.valueOf(t.execute(m));
        }
        return super.toString();
    }

}