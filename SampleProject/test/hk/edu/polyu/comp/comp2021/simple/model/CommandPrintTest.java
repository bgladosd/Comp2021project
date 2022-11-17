package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandPrintTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandPrint("print print1 1".split(" "), m);
        String printString = "print1 added";
        assertEquals(printString, outContent.toString().trim());
        assertEquals(c1 ,m.getCmd("print1"));
        assertEquals("print1", m.getCmd("print1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandPrint("print print1 1 ele4".split(" "), m);
        String failStat = "instruction failed! print statement should only have 3 elements which is (print lab expRef)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandPrint("print print 1".split(" "), m);
        String failStat = "print is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandPrint("print print1 A12345678".split(" "), m);
        String failStat = "A12345678 is not valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandPrint("print print1 1".split(" "), m);
        c1.execute(m);
        String printString = "print1 added";
        assertEquals(printString, outContent.toString().split("\n")[0].trim());
        String result = "[1]";
        assertEquals(result ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandPrint("print print1 true".split(" "), m);
        c1.execute(m);
        String printString = "print1 added";
        assertEquals(printString, outContent.toString().split("\n")[0].trim());
        String result = "[true]";
        assertEquals(result ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testExecute3() {
        Command c1 = new CommandPrint("print print1 x".split(" "), m);
        c1.execute(m);
        String printString = "print1 added";
        assertEquals(printString, outContent.toString().split("\n")[0].trim());
        String result = "[100]";
        assertEquals(result ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testExecuteFail() {
        Command c1 = new CommandPrint("print print1 y".split(" "), m);
        c1.execute(m);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "ExpRef is not a valid data";
        assertEquals(failStat ,outContent.toString().split("\n")[1].trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}