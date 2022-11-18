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
            command.setCmdString(c);
        } else if (cmd[0].equals("binexpr")) {
            command = new CommandBinexpr(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("unexpr")) {
            command = new CommandUnexpr(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("assign")) {
            command = new CommandAssign(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("print")) {
            command = new CommandPrint(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("skip")) {
            command = new CommandSkip(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("if")) {
            command = new CommandIf(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("load")) {
            new CommandLoad(cmd, m);
        } else if (cmd[0].equals("togglebreakpoint")) {
            new CommandToggleBreakPoint(cmd, m);
        } else if (cmd[0].equals("debug")) {
            new CommandDebug(cmd, m);
        } else if (cmd[0].equals("execute")) {
            command = new CommandExecute(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("while")) {
            command = new CommandWhile(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("block")) {
            command = new CommandBlock(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("program")) {
            command = new CommandProgram(cmd, m);
            command.setCmdString(c);
        } else if (cmd[0].equals("store")) {
            new CommandStore(cmd, m);
        }else if (cmd[0].equals("instrument")) {
            new CommandInstrument(cmd, m);
        }

        // for testing delete later
        else if (cmd[0].equals("s")) {
            m.SaveState();
        } else if (cmd[0].equals("l")) {
            m.LoadState();
        } else if (cmd[0].equals("exeMode")) {
            if (executeMode) {
                executeMode = false;
            } else {
                executeMode = true;
            }
            System.out.println("exe mode : = " + executeMode);

        }

        else {
            System.out.println("Command not found : " + cmd[0]);
            return;
        }
        // if (executeMode) {
        // command.execute(m);
        // }

    }

}