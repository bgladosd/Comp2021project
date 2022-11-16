package hk.edu.polyu.comp.comp2021.simple.model;

//this should execute program, but for testing, it is also able to use to execute Commands
public class CommandExecute implements Command {

    String label;

    String exeLabel;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getProgram(exeLabel);
        if (c==null) {
            System.out.println("Program : "+ exeLabel +" can not be found ");
            return null;
        }
        // c=m.getCmd(exeLabel);
        c.execute(m);
        return null;

    }

    public CommandExecute(String[] cmd, Memory m) {
        if (cmd.length != 2) {
            System.out.println(
                    "instruction failed! execute statement should only have 2 elements which is (execute program1)");
            return;
        }
        exeLabel = cmd[1];

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

}