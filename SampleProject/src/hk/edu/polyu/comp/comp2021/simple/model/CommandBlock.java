package hk.edu.polyu.comp.comp2021.simple.model;

import java.util.ArrayList;

public class CommandBlock implements Command {
    String cmdString;
    String label;
    ArrayList<String> cmdListString= new ArrayList<String>();
    ArrayList<Command> cmdList=new ArrayList<Command>();

    @Override
    public DataObject execute(Memory m) {
        cmdList=new ArrayList<Command>();
        for (String command : cmdListString) {
            if (m.getCmd(command)==null) {
                System.out.println("Statement : "+ command +" can not be found ");
            }
            cmdList.add(m.getCmd(command)) ;
        }
        for (Command command : cmdList) {
            command.execute(m);
        }
        return null;

    }

    public CommandBlock(String[] cmd, Memory m) {
        if (cmd.length <3) {
            System.out.println(
                    "instruction failed! block statement should have atleast 3 elements which in format of (block lab expRef statementLab1 ... statementLabn)");
            return;
        }
        label = cmd[1];

        for (int i = 2; i < cmd.length; i++) {
            //check
            cmdListString.add(cmd[i]);

        }


        // command check tegrity end
        m.addCmd(label, this);

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