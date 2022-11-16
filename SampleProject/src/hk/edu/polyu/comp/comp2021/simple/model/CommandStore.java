package hk.edu.polyu.comp.comp2021.simple.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommandStore implements Command{

    String path;
    String progName;
    String label;


    @Override
    public DataObject execute(Memory m) {

        try {
            File store = new File(path+"/"+progName+".txt");
            if (store.createNewFile()) {
                //if no such txt file, create a new file

                try {
                    FileWriter txtWriter = new FileWriter(path+"/"+progName+".txt");
                    txtWriter.write("Hi");
                    txtWriter.close();
                } catch (IOException e) {
                    System.out.println("Error: Failed to writing txt file.");
                    e.printStackTrace();
                }

                System.out.println(store.getName()+ " created");
            } else {
                //if have such txt file, rewrite it

                try {
                    FileWriter txtWriter = new FileWriter(path+"/"+progName+".txt");
                    txtWriter.write("rewrite Hi");
                    txtWriter.close();
                } catch (IOException e) {
                    System.out.println("Error: Failed to writing txt file.");
                    e.printStackTrace();
                }
                System.out.println("Same name file already exists so it is rewritten.");
            }

        } catch (IOException e) {
            System.out.println("Error: Failed to creating txt file.");
            e.printStackTrace();
        }

        return null;
    }

    public CommandStore(String[] cmd, Memory m){
        if(cmd.length!=3){            //store printeven C:\\
            System.out.println(
                    "instruction failed! store statement should only have 3 elements which is (store programName path)");
            return;
        }

        this.progName=cmd[1];
        this.path=cmd[2];

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
}