package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandListTest {
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
        m.setRunningProgramName("program1");
        Command c1 = new CommandList("list program1".split(" "), m);
        String[] printString = {"[100]", "program program1 block1", "block block1 vardef1 print1", "vardef vardef1 int x 100", "print print1 x"};
        assertEquals(printString[0] ,outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1] ,outContent.toString().split("\n")[1].trim());
        assertEquals(printString[2] ,outContent.toString().split("\n")[2].trim());
        assertEquals(printString[3] ,outContent.toString().split("\n")[3].trim());
        assertEquals(printString[4] ,outContent.toString().split("\n")[4].trim());
    }

    @Test
    public void testConstructorExecute2 () {
        Command c1 = new CommandList("list program1 3rdEle".split(" "), m);
        String failStat = "instruction failed! list statement should only have 2 elements which is (list programName)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}