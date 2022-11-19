package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandToggleBreakPointTest {
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
        Command c1 = new CommandToggleBreakPoint("togglebreakpoint program1 print1".split(" "), m);
        String printString = "break point : program1 print1 added";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute2 () {
        Command c1 = new CommandToggleBreakPoint("togglebreakpoint program1 print1".split(" "), m);
        Command c2 = new CommandToggleBreakPoint("togglebreakpoint program1 print1".split(" "), m);
        String printString = "break point : program1 print1 removed";
        assertEquals(printString ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testConstructorExecute3 () {
        Command c1 = new CommandToggleBreakPoint("togglebreakpoint program1 print1 4thEle".split(" "), m);
        String failStat = "instruction failed! togglebreakpoint statement should only have 3 elements which is (togglebreakpoint programName statementLab)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}