package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandLoadTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command cb = new CommandBlock("block block1 vardef1 print1".split(" "), m);
    Command cpg = new CommandProgram("program program1 block1".split(" "), m);

    String path = System.getProperty("user.dir");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.setCmdString("vardef vardef1 int x 100");
        cp.setCmdString("print print1 x");
        cb.setCmdString("block block1 vardef1 print1");
        cpg.setCmdString("program program1 block1");
        Command c2 = new CommandStore(("store program1 "+path).split(" "), m);
    }

    @Test
    public void testConstructorExecute () {
        Command c2 = new CommandLoad(("load "+path+ " program1").split(" "), m);
        Command c3 = new CommandExecute("execute program1".split(" "), m);
        String printString = "[100]";
        String [] output = outContent.toString().split("\n");
        assertEquals(printString ,output[output.length-1].trim());
    }

    @Test
    public void testConstructorExecute2 () {
        Command c2 = new CommandLoad(("load "+path+ " program2").split(" "), m);
        String failStat = "file not found, please ensure that your file path is correct";
        assertEquals(failStat ,outContent.toString().split("\n")[2].trim());
    }

    @Test
    public void testConstructorExecute3 () {
        Command c2 = new CommandLoad("load d:\\simple_testing program1 4thEle".split(" "), m);
        String failStat = "instruction failed! load statement should only have 3 elements which is (load path programName)";
        assertEquals(failStat ,outContent.toString().split("\n")[2].trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}