package hk.edu.polyu.comp.comp2021.simple.model;

public class CommandList implements Command{

    String progName;
    String label;

    @Override
    public DataObject execute(Memory m) {
        Command c = m.getProgram(progName);
        if (c == null) {
            System.out.println("Program : " + progName + " can not be found ");
            return null;
        }

        m.reSetVariableData();
        m.setRunningProgramName(null);
        m.resetRunnedCommand();
        m.setExecuting(true);
        c.execute(m);
        m.setExecuting(false);

        while (!m.getRunnedCommand().isEmpty()) {
            Command cs = m.getRunnedCommand().remove();
            System.out.println(cs.getCmdString());
        }


        return null;
    }

    public CommandList(String[] cmd, Memory m){
        if(cmd.length!=2){
            System.out.println(
                    "instruction failed! list statement should only have 2 elements which is (list programName)");
            return;
        }

        this.progName=cmd[1];

        this.execute(m);
    }






    @Override
    public void setLabel(String l) {
        this.label=l;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getCmdString() {
        return null;
    }

    @Override
    public void setCmdString(String s) {

    }
}
