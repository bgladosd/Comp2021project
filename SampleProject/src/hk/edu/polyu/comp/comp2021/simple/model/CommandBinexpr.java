package hk.edu.polyu.comp.comp2021.simple.model;


public class CommandBinexpr implements Command {
    
    String label;
    String dataName;
    DataObject dataObject;

    String expName ;
    String expRef1 ;
    String bop ;
    String expRef2 ;
    @Override
    public DataObject execute(Memory m)  {
        m.addData(expName, new DataObject(this,m));    
        
        DataObject expRefObject1 =new DataObject(expRef1,m);
        DataObject expRefObject2 =new DataObject(expRef2,m);
        if (expRefObject1.type=="i") {
            Integer intExpRef1=(Integer)expRefObject1.o;
            Integer intExpRef2=(Integer)expRefObject2.o;
            switch (bop) {
                case "%":
                dataObject= new DataObject(String.valueOf((intExpRef1 % intExpRef2)),m);
                break;
                case "+":
                dataObject= new DataObject(String.valueOf((intExpRef1 + intExpRef2)),m);
                break;
                case "-":
                dataObject= new DataObject(String.valueOf((intExpRef1 - intExpRef2)),m);
                break;
                case "*":
                dataObject= new DataObject(String.valueOf((intExpRef1 * intExpRef2)),m);
                break;
                case "/":
                dataObject= new DataObject(String.valueOf((intExpRef1 / intExpRef2)),m);
                break;
                case ">":
                dataObject= new DataObject(String.valueOf((intExpRef1 > intExpRef2)),m);
                break;
                case ">=":
                dataObject= new DataObject(String.valueOf((intExpRef1 >= intExpRef2)),m);
                break;
                case "<":
                dataObject= new DataObject(String.valueOf((intExpRef1 < intExpRef2)),m);
                break;
                case "<=":
                dataObject= new DataObject(String.valueOf((intExpRef1 <= intExpRef2)),m);
                break;
                case "==":
                dataObject= new DataObject(String.valueOf((intExpRef1 == intExpRef2)),m);
                break;
                case "!=":
                dataObject= new DataObject(String.valueOf((intExpRef1 != intExpRef2)),m);
                break;
            }
        } else {
            Boolean boolExpRef1=(Boolean)expRefObject1.o;
            Boolean boolExpRef2=(Boolean)expRefObject2.o;
            switch (bop) {
                case "==":
                dataObject= new DataObject(String.valueOf((boolExpRef1 == boolExpRef2)),m);
                break;
                case "!=":
                dataObject= new DataObject(String.valueOf((boolExpRef1 != boolExpRef2)),m);
                break;
                case "||":
                dataObject= new DataObject(String.valueOf((boolExpRef1 || boolExpRef2)),m);
                break;
                case "&&":
                dataObject= new DataObject(String.valueOf((boolExpRef1 && boolExpRef2)),m);
                break;
            }
        }
        return dataObject;

    }
    public CommandBinexpr(String[] cmd,Memory m) {
        if (cmd.length != 5) {
            System.out.println(
                    "instruction failed! binexpr statement should only have 5 elements which is (binexpr expName expRef1 bop expRef2)");
            return;
        }
        expName = cmd[1];
        expRef1 = cmd[2];
        bop = cmd[3];
        expRef2 = cmd[4];

        DataObject expRefObject1 =new DataObject();
        DataObject expRefObject2 =new DataObject();

        if (expRefObject1.autoSetData(expRef1,m)) {
        }else{
            System.out.println(
                    "ExpRef1 is not a valid data");
            return;
        }
        if (expRefObject2.autoSetData(expRef2,m)) {
        }else{
            System.out.println(
                    "ExpRef2 is not a valid data");
            return;
        }
        //can't do calculation if they are not the same type
        if (!expRefObject1.type.equals(expRefObject2.type)) {
            System.out.println(
                    "can't do calculation if ExpRef1 and ExpRef2 are not the same type");
            return;
        }

        // command check tegrity end
        m.addCmd(expName, this);
        
        
        
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