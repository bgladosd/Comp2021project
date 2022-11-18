package hk.edu.polyu.comp.comp2021.simple.model;

//this should execute program, but for testing, it is also able to use to execute Commands
public class CommandDebug implements Command {

    String label;

    String program;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getProgram(program);
        if (c==null) {
            System.out.println("Program : "+ program +" can not be found ");
            return null;
        }
        m.setDebugMode(true);
        CommandExecute ce =new CommandExecute(new String[]{"execute",program}, m);
        m.setDebugMode(false);
        return null;

    }

    public CommandDebug(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! execute statement should only have 2 elements which are (debug program1)");
            return;
        }
        program = cmd[1];

        this.execute(m);

    }

    @Override
    public void setLabel(String l) {
        this.label = l;

    }

    @Override
    public String getLabel() {
        return label;
    }
    
    @Override
    public String getCmdString() {
        return "";
    }

    @Override
    public void setCmdString(String s) {
        
    }


}
