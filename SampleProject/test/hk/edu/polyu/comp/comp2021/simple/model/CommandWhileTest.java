package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandWhileTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command cb = new CommandBinexpr("binexpr exp1 x + 1".split(" "), m);
    Command ca = new CommandAssign("assign assign1 x exp1".split(" "), m);
    Command cb2 = new CommandBinexpr("binexpr exp2 x <= 105".split(" "), m);
    Command cbl = new CommandBlock("block block1 assign1 print1".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandWhile("while while1 true print1".split(" "), m);
        assertEquals(c1 ,m.getCmd("while1"));
        assertEquals("while1", m.getCmd("while1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandWhile("while while1 true print1 5thEle".split(" "), m);
        String failStat = "instruction failed! if statement should only have 4 elements which is (while lab expRef statementLab1)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandWhile("while while true print1".split(" "), m);
        String failStat = "while is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandWhile("while while1 true print".split(" "), m);
        String failStat = "print is not a valid statement label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor5() {
        Command c1 = new CommandWhile("while while1 A12345678 print".split(" "), m);
        String failStat = "A12345678 is not a valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandWhile("while while1 exp2 block1".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String [] printString = {"[101]", "[102]", "[103]", "[104]", "[105]"};
        assertEquals(printString[0] ,outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1] ,outContent.toString().split("\n")[1].trim());
        assertEquals(printString[2] ,outContent.toString().split("\n")[2].trim());
        assertEquals(printString[3] ,outContent.toString().split("\n")[3].trim());
        assertEquals(printString[4] ,outContent.toString().split("\n")[4].trim());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandWhile("while while1 exp2 block2".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String failStat = "execute while1 failed! statementLab1 can not be find";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute3() {
        Command c1 = new CommandWhile("while while1 abc block1".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String failStat = "execute while1 failed! unable to load expRef";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute4() {
        Command c1 = new CommandWhile("while while1 123 block1".split(" "), m);
        m.setExecuting(true);
        c1.execute(m);
        String failStat = "execute while1 failed! expRef is not boolean";
        assertEquals(failStat ,outContent.toString().trim());
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}