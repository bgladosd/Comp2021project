package hk.edu.polyu.comp.comp2021.simple.model;

public class DataObject {
    private String type;
    private Object o;
    private Memory m;

    public DataObject() {

    }

    public DataObject(String s, Memory m) {
        autoSetData(s, m);
    }

    public DataObject(String type, Object o, Memory m) {
        this.type = type;
        this.o = o;
        this.m = m;
    }

    public DataObject(Command c, Memory m) {
        this.type = "e";
        this.o = c;
        this.m = m;
    }

    public String getType() {
        return type;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBoolean(Boolean b) {
        o = b;
        type = "bool";
    }

    public void setInteger(Integer i) {
        o = i;
        type = "int";
    }

    public boolean autoSetData(String s, Memory m) {
        // is variable that inside memory
        this.m = m;
        if (m.getData(s) != null) {
            this.o = m.getData(s).o;
            this.type = m.getData(s).type;
            if (m.getData(s).type.equals("e")) {
                if (!m.getExecuting()) {
                    return true;
                }

                while (this.type.equals("e")) {
                    Command tcc = (Command) this.o;
                    this.o = tcc.execute(m).o;
                    this.type = tcc.execute(m).type;
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
            if (t > 99999) {
                t = 99999;
            } else if (t < -99999) {
                t = -99999;
            }
            this.o = t;
            return true;
        }

        return false;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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