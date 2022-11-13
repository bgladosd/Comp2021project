package hk.edu.polyu.comp.comp2021.simple.model;


public class CommandVarDef implements Command {
    String label;
    String dataName;
    DataObject dataObject;
    @Override
    public DataObject execute(Memory m)  {
        
        m.addData(dataName, dataObject);
        return(null);
    }
    public CommandVarDef(String[] cmd,Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! vardef statement should only have 5 elements which is (vardef lable type varName expRef)");
            return;
        }
        String label = cmd[1];
        String type = cmd[2];
        String varName = cmd[3];
        String expRef = cmd[4];
        // if (checkValidName(cmd[1])) {

        // }

        //check label exist

        //check variable exist

        // should have more check
        // command check tegrity end
        DataObject dataO = new DataObject();

        if (type.equals("int")) {
            int o;
            try {
                o = Integer.parseInt(expRef);
            } catch (Exception e) {
                System.out.println("instruction failed! expRef is not valid integer!");
                return;
            }
            if (o > 99999) {
                o = 99999;
            } else if (o < -99999) {
                o = -99999;
            }
            dataO.setO(o);
            dataO.setType("i");
        }
        if (type.equals("bool")) {
            if (expRef.equals("true")) {
                dataO.o = true;
            } else if (expRef.equals("false")) {
                dataO.o = false;
            } else {
                System.out.println(
                        "instruction failed! expRef is not valid boolean! make true it is true or false in lowercase.");
                return;
            }
            dataO.setType("b");
        }
        
        setLabel(label);
        setDataObject(dataO);
        setDataName(varName);

        m.addCmd(label, this);
        
    }
    @Override
    public void setLabel(String l) {
        this.label=l;
        
    }
    @Override
    public String getLabel() {
        return label;
    }
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    

    
}