package hk.edu.polyu.comp.comp2021.simple.model;


public class CommandProgram implements Command {

    String label;
    String statemendLabel;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getCmd(statemendLabel);
        if (c==null) {
            System.out.println("Statemend : "+ statemendLabel +" can not be found ");
            return null;
        }
        c.execute(m);

        return null;
    }

    public CommandProgram(String[] cmd, Memory m) {
        if (cmd.length !=3) {
            System.out.println(
                "instruction failed! program statement should only have 3 elements which is (program programName statementLab)");
            return;
        }
        label = cmd[1];
        statemendLabel = cmd[2];
        if (!m.checkIsValidProgramName(label)) {
            System.out.println("program name is not valid");
        }
        if (!m.checkIsValidNameOrLabel(statemendLabel)) {
            System.out.println("statement Label name is not valid");
        }
        


        // command check tegrity end
        
        m.addProgram(label, this);

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