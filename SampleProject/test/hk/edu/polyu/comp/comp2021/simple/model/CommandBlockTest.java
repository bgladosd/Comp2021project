package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandBlockTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command ca = new CommandAssign("assign assign1 x 5".split(" "), m);
    Command cs = new CommandSkip("skip skip1".split(" "), m);
    Command ca2 = new CommandAssign("assign assign2 x 50".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandBlock("block block1 assign1 skip1".split(" "), m);
        assertEquals(c1 ,m.getCmd("block1"));
        assertEquals("block1", m.getCmd("block1").getLabel());

    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandBlock("block block1".split(" "), m);
        String failStat = "instruction failed! block statement should have at least 3 elements which in format of (block lab statementLab1 ... statementLabn)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandBlock("block block assign1 skip1".split(" "), m);
        String failStat = "block is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandBlock("block block1 assign skip1".split(" "), m);
        String failStat = "assign is not a valid statement label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor5 () {
        Command c1 = new CommandBlock("block block1 assign1 skip".split(" "), m);
        String failStat = "skip is not a valid statement label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandBlock("block block1 assign1 skip1".split(" "), m);
        c1.execute(m);
        assertEquals(5, m.getData("x").getO());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandBlock("block block1 assign1 skip1 assign2 print1".split(" "), m);
        c1.execute(m);
        assertEquals(50, m.getData("x").getO());
        String result = "[50]";
        assertEquals(result ,outContent.toString().trim());
    }

    @Test
    public void testExecuteFail() {
        Command c1 = new CommandBlock("block block1 assign1 skip1 abc".split(" "), m);
        c1.execute(m);
        String result = "Statement : abc can not be found";
        assertEquals(result ,outContent.toString().trim());
        assertEquals(false, c1.execute(m).getO());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}