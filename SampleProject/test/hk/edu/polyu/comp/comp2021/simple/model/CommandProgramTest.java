package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandProgramTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandProgram("program program1 print1".split(" "), m);
        assertEquals(c1, m.getProgram("program1"));
        assertEquals("program1", m.getProgram("program1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandProgram("program program1 print1 4thEle".split(" "), m);
        String failStat = "instruction failed! program statement should only have 3 elements which is (program programName statementLab)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandProgram("program program print1".split(" "), m);
        String failStat = "program name is not valid";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandProgram("program program1 print".split(" "), m);
        String failStat = "print is not a valid statement label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandProgram("program program1 print1".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String printString = "[100]";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandProgram("program program1 abc".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String failStat = "execute abc failed! statementLab1 can not be found";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


}