package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void buildCommand(String c, Memory m) {

        String[] cmd = c.split(" ");
        // vardef command
        if (cmd[0].equals("vardef")) {
            
            CommandVarDef cvd = new CommandVarDef(cmd,m);
            // DataObject to;
            // if (m.getData("vardef1").type.equals("c")) {
            //     Command cm= (Command)m.getData("vardef1").o;
            //     to=cm.execute(m);
            // }
            Command cm= (Command)m.getData("vardef1").o;
            cm.execute(m);

            System.out.println(m.getData("x").o);
                 
        }

    }

}