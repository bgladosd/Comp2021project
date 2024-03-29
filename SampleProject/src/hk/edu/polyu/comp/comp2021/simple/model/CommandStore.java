package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/** store command. this command run program insilent mode once and store the used command to a text file
 * @author kk335577xyz
 */
//run before program load
public class CommandStore implements Command {

    private String path;
    private String progName;
    private String label;

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
        m.setRunningProgramName("");

        try {
            File store = new File(path + "/" + progName + ".txt");
            if (store.createNewFile()) {
                // if no such txt file, create a new file

                try {
                    FileWriter txtWriter = new FileWriter(path + "/" + progName + ".txt");
                    // print out all the runned command
                    while (!m.getRunnedCommand().isEmpty()) {
                        Command cs = m.getRunnedCommand().remove();
                        txtWriter.write(cs.getCmdString() + "\n");
                    }
                    txtWriter.write("execute " + progName + "\n");
                    txtWriter.close();
                } catch (IOException e) {
                    System.out.println("Error: Failed to writing txt file.");
                    System.out.println(e);
                }

                System.out.println(store.getName() + " created");
            } else {
                // if have such txt file, rewrite it

                try {
                    FileWriter txtWriter = new FileWriter(path + "/" + progName + ".txt");
                    // print out all the runned command
                    while (!m.getRunnedCommand().isEmpty()) {
                        Command cs = m.getRunnedCommand().remove();
                        txtWriter.write(cs.getCmdString() + "\n");
                    }
                    txtWriter.write("execute " + progName + "\n");
                    txtWriter.close();
                } catch (IOException e) {
                    System.out.println("Error: Failed to writing txt file.");
                    System.out.println(e);
                }
                System.out.println("Same name file already exists so it is rewritten.");
            }

        } catch (IOException e) {
            System.out.println("Error: Failed to creating txt file.");
            System.out.println(e);

        }

        return null;
    }
    /** check is the command valid and execute command to store program
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandStore(String[] cmd, Memory m) {
        if (cmd.length != 3) { // store printeven C:\\
            System.out.println(
                    "instruction failed! store statement should only have 3 elements which is (store programName path)");
            return;
        }

        this.progName = cmd[1];
        this.path = cmd[2];

        this.execute(m);

    }

    @Override
    public void setLabel(String l) {
        this.label = l;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getCmdString() {
        return "";
    }

    @Override
    public void setCmdString(String s) {

    }
}
