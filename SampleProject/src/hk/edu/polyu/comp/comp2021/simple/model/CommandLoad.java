package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.BufferedReader;
import java.io.FileReader;
/** load command. this command load all the input from a file
 * @author Jack Lee
 */
public class CommandLoad implements Command {

    private String path;
    private String progName;
    private String label;

    CommandBuilder cmdB;

    @Override
    public DataObject execute(Memory m) {
        cmdB = new CommandBuilder();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path + "/" + progName + ".txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                cmdB.buildCommand(line, m);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Load success");
        } catch (Exception e) {
            System.out.println("file not found, please ensure that your file path is correct");
            System.out.println(e);
            System.out.println(e.getStackTrace()[0]);
        }

        return null;
    }
    /** check is the command valid and execute to load all command in a file
     * @param cmd command arguments
     * @param m access to memory
     */
    public CommandLoad(String[] cmd, Memory m) {
        if (cmd.length != 3) { // load D:\test test
            System.out.println(
                    "instruction failed! load statement should only have 3 elements which is (load path programName)");
            return;
        }
        this.path = cmd[1];
        this.progName = cmd[2];

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
