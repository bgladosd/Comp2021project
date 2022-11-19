package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandProgram implements Command {
    String cmdString;
    String label;
    String statementLabel;

    @Override
    public DataObject execute(Memory m) {
        m.preExecution(this);
        Command c = m.getCmd(statementLabel);
        if (c == null) {
            System.out.println("execute " + statementLabel + " failed! statementLab1 can not be found");
            return null;
        }
        c.execute(m);
        m.postExecution(this);
        return null;
    }

    public CommandProgram(String[] cmd, Memory m) {
        if (cmd.length != 3) {
            System.out.println(
                    "instruction failed! program statement should only have 3 elements which is (program programName statementLab)");
            return;
        }
        String ProgramLabel = cmd[1];
        statementLabel = cmd[2];
        if (!m.checkIsValidProgramName(ProgramLabel)) {
            System.out.println("program name is not valid");
        }
        if (!m.checkIsValidNameOrLabel(statementLabel)) {
            System.out.println(statementLabel + " is not a valid statement label name");
        }

        // command check tegrity end
        setLabel(ProgramLabel);
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

    @Override
    public String getCmdString() {
        return this.cmdString;
    }

    @Override
    public void setCmdString(String s) {
        this.cmdString = s;
    }

}