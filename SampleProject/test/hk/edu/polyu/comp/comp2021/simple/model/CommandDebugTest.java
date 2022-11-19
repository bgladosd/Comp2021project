package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandDebugTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command ca = new CommandAssign("assign assign1 x 300".split(" "), m);
    Command cb = new CommandBlock("block block1 vardef1 print1 assign1 print1".split(" "), m);
    Command cpg = new CommandProgram("program program1 block1".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testConstructorExecute () {
        Command c1 = new CommandDebug("debug program1".split(" "), m);
        String printString = "[300]";
        assertEquals(printString ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testConstructorExecute2 () {
        Command c1 = new CommandDebug("debug program1 3rdEle".split(" "), m);
        String failStat = "instruction failed! execute statement should only have 2 elements which are (debug program1)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute3 () {
        Command c1 = new CommandDebug("debug program2".split(" "), m);
        String failStat = "Program : program2 can not be found";
        assertEquals(failStat ,outContent.toString().trim());
    }

}