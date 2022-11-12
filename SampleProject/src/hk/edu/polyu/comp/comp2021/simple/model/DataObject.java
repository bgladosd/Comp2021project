package hk.edu.polyu.comp.comp2021.simple.model;


public class DataObject {
    String type;
    Object o;
    public DataObject(String type,Object o) {
        this.type=type;
        this.o=o;
    }
    public DataObject() {
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
}