package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.IOException;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void buildCommand(String c, Memory m) {

        String[] cmd = c.split(" ");
        // vardef command
        if (cmd[0].equals("vardef")) {
            System.out.println("help");
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
            CommandVarDef cvd = new CommandVarDef(label, varName, dataO);
            cvd.execute(m);

            System.out.println("try get");
            if (m.getData("x").type.equals("b")) {
                System.out.println(((Boolean) m.getData("x").o));
            }

        }

    }

}