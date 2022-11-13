package hk.edu.polyu.comp.comp2021.simple.model;


public class DataObject {
    String type;
    Object o;
    Memory m;
    public DataObject() {

    }
    public DataObject(String s,Memory m) {
        autoSetData(s, m);
    }
    public DataObject(String type,Object o,Memory m) {
        this.type=type;
        this.o=o;
        this.m=m;
    }
    public DataObject(Command c,Memory m) {
        this.type="e";
        this.o=c;
        this.m =m;
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
    public void setBoolean(Boolean b){
        o=b;
        type="b";        
    }
    public void setInteger(Integer i){
        o=i;
        type="i";        
    }
    public boolean autoSetData(String s,Memory m){
        //is variable that inside memory
        this.m = m;
        if (m.getData(s)!=null) {
            if (m.getData(s).type.equals("e")) {
                Command tc= (Command)m.getData(s).o;
                this.o= tc.execute(m).o;
                this.type= tc.execute(m).type;
                while (this.type.equals("e")) {
                    Command tcc= (Command)this.o;
                    this.o= tcc.execute(m).o;
                    this.type= tcc.execute(m).type;
                }
                
                return true;
            }else{
                this.o=m.getData(s).o;
                this.type=m.getData(s).type;
                return true;
            }
        //is a boolean
        }else if (s.equals("true") || s.equals("false")) {
            this.type="b";
            this.o=Boolean.parseBoolean(s);
            return true;
        //is a integer
        }else if (isInteger(s)) {
            this.type="i";
            int t= Integer.parseInt(s);
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

    public boolean isInteger(String s){
        try {
            Integer.parseInt( s );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        if (type.equals("i")) {
            int t = (int)o;
            return String.valueOf(t);
        }
        if (type.equals("b")) {
            Boolean t = (Boolean)o;
            return String.valueOf(t);
        }
        if (type.equals("e")) {
            Command t = (Command)o;
            return String.valueOf(t.execute(m));
        }
        return super.toString();
    }
    
}