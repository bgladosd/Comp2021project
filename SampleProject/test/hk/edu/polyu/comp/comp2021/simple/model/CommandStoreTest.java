package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class CommandStoreTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command cb = new CommandBlock("block block1 vardef1 print1".split(" "), m);
    Command cpg = new CommandProgram("program program1 block1".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.setCmdString("vardef vardef1 int x 100");
        cp.setCmdString("print print1 x");
        cb.setCmdString("block block1 vardef1 print1");
        cpg.setCmdString("program program1 block1");
    }

    @Test
    public void testConstructorExecute () {
        //Command c1 = new CommandStore("store program1 d:\\simple_testing".split(" "), m);
        File myObj = new File("d:\\simple_testing\\program1.txt");
        myObj.delete();
        Command c2 = new CommandStore("store program1 d:\\simple_testing".split(" "), m);
        String path = "d:\\simple_testing";
        String progName = "program1";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path+"\\"+progName+".txt"));
        } catch (Exception e) {}
        assertNotNull(reader);
    }

    @Test
    public void testConstructorExecute2 () { //for rewrite
        Command c1 = new CommandStore("store program1 d:\\simple_testing".split(" "), m);
        Command c2 = new CommandStore("store program1 d:\\simple_testing".split(" "), m);
        String path = "d:\\simple_testing";
        String progName = "program1";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path+"\\"+progName+".txt"));
        } catch (Exception e) {}
        assertNotNull(reader);
        String rewriteStat = "Same name file already exists so it is rewritten.";
        assertEquals(rewriteStat ,outContent.toString().split("\n")[1].trim());

    }

    @Test
    public void testConstructorExecute3 () {
        Command c1 = new CommandStore("store program1 d:\\simple_testing 4thEle".split(" "), m);
        String failStat = "instruction failed! store statement should only have 3 elements which is (store programName path)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}