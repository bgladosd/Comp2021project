package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void buildCommand(String c, Memory m) {

        String[] cmd = c.split(" ");
        // vardef command
        if (cmd[0].equals("vardef")) {
            
            CommandVarDef cvd = new CommandVarDef(cmd,m);
            m.getCmd("vardef1").execute(m);

            System.out.println("vardef test x : "+m.getData("x"));
                 
        }else if (cmd[0].equals("binexpr")) {
            CommandBinexpr cb = new CommandBinexpr(cmd, m);
            m.getCmd("exp1").execute(m);

            System.out.println("binexpr test : "+m.getData("exp1"));
        }
        else if (cmd[0].equals("inspect")) {
            CommandInspect ci = new CommandInspect(cmd, m);
            ci.execute(m);
        }

    }

}