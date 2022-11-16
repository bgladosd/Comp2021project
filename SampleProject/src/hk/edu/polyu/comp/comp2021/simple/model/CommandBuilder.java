package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void buildCommand(String c, Memory m) {
        Boolean executeMode = false;

        String[] cmd = c.split(" ");
        // Command command = new CommandSkip(new String[]{"skip","shit"}, m);
        Command command;
        // vardef command
        if (cmd[0].equals("vardef")) {
            command = new CommandVarDef(cmd, m);
        } else if (cmd[0].equals("binexpr")) {
            command = new CommandBinexpr(cmd, m);
        } else if (cmd[0].equals("unexpr")) {
            command = new CommandUnexpr(cmd, m);
        } else if (cmd[0].equals("assign")) {
            command = new CommandAssign(cmd, m);
        } else if (cmd[0].equals("print")) {
            command = new CommandPrint(cmd, m);
        } else if (cmd[0].equals("skip")) {
            command = new CommandSkip(cmd, m);
        } else if (cmd[0].equals("inspect")) {
            new CommandInspect(cmd, m);
        } else if (cmd[0].equals("if")) {
            command = new CommandIf(cmd, m);
        } else if (cmd[0].equals("load")) {
            new CommandLoad(cmd, m);
        } else if (cmd[0].equals("execute")) {
            new CommandExecute(cmd, m);
        } else if (cmd[0].equals("while")) {
            command = new CommandWhile(cmd, m);
        } else if (cmd[0].equals("block")) {
            command = new CommandBlock(cmd, m);
        } else if (cmd[0].equals("program")) {
            CommandProgram cP = new CommandProgram(cmd, m);
        } else if (cmd[0].equals("exeMode")) {
            if (executeMode) {
                executeMode=false;
            }else{executeMode=true;}
            System.out.println("exe mode : = "+ executeMode);

        } else {
            System.out.println("Command not found : " + cmd[0]);
            return;
        }
        // if (executeMode) {
        //     command.execute(m);
        // }

    }

}