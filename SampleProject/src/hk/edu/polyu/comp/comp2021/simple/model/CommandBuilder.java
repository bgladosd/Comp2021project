package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void buildCommand(String c, Memory m) {

        String[] cmd = c.split(" ");
        // vardef command
        if (cmd[0].equals("vardef")) {
            CommandVarDef cvd = new CommandVarDef(cmd, m);
        } else if (cmd[0].equals("binexpr")) {
            CommandBinexpr cb = new CommandBinexpr(cmd, m);
        } else if (cmd[0].equals("unexpr")) {
            CommandUnexpr cu = new CommandUnexpr(cmd, m);
        } else if (cmd[0].equals("inspect")) {
            CommandInspect ci = new CommandInspect(cmd, m);
           
        } else if (cmd[0].equals("if")) {
            CommandIf cif = new CommandIf(cmd, m);
        } else if (cmd[0].equals("load")) {
            CommandLoad cl = new CommandLoad(cmd, m);
            cl.execute(m);
        } else if (cmd[0].equals("execute")) {
            CommandExecute cl = new CommandExecute(cmd, m);
        }

    }

}