package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandIfTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cv2 = new CommandVarDef("vardef vardef2 int y 55".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command cp2 = new CommandPrint("print print2 y".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
        cv2.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandIf("if if1 exp1 print1 print2".split(" "), m);
        assertEquals(c1 ,m.getCmd("if1"));
        assertEquals("if1", m.getCmd("if1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandIf("if if1 exp1 print1 print2 6thEle".split(" "), m);
        String failStat = "instruction failed! if statement should only have 5 elements which is (if lab expRef statementLab1 statementLab2)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandIf("if if exp1 print1 print2".split(" "), m);
        String failStat = "if is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandIf("if if1 exp1 print print2".split(" "), m);
        String failStat = "print is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor5 () {
        Command c1 = new CommandIf("if if1 exp1 print1 assign".split(" "), m);
        String failStat = "assign is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor6() {
        Command c1 = new CommandIf("if if1 A12345678 print1 print2".split(" "), m);
        String failStat = "A12345678 is not a valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandIf("if if1 true print1 print2".split(" "), m);
        m.setExecuting(true);
        m.setRunningProgramName("Testing");
        c1.execute(m);
        String printString = "[100]";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandIf("if if1 false print1 print2".split(" "), m);
        m.setExecuting(true);
        m.setRunningProgramName("Testing");
        c1.execute(m);
        String printString = "[55]";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testExecute3() {
        Command c1 = new CommandIf("if if1 abc print1 print2".split(" "), m);
        c1.execute(m);
        String failStat = "execute if1 failed! unable to load expRef";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute4() {
        Command c1 = new CommandIf("if if1 123 print1 print2".split(" "), m);
        c1.execute(m);
        String failStat = "execute if1 failed! expRef is not boolean";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute5() {
        Command c1 = new CommandIf("if if1 true abc print2".split(" "), m);
        c1.execute(m);
        String failStat = "execute if1 failed! statementLab1 can not be find";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute6() {
        Command c1 = new CommandIf("if if1 false print1 abc".split(" "), m);
        c1.execute(m);
        String failStat = "execute if1 failed! statementLab2 can not be find";
        assertEquals(failStat ,outContent.toString().trim());
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}