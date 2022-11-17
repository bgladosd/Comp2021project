package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandUnexprTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ 20".split(" "), m);
        assertEquals(c1 ,m.getData("exp2").getO());
        assertEquals("e", m.getData("exp2").getType());
        assertNull(c1.getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ 20 5thEle".split(" "), m);
        String failStat = "instruction failed! unexpr statement should only have 4 elements which is (unexpr expName uop expRef1)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandUnexpr("unexpr unexpr ~ 20".split(" "), m);
        String failStat = "unexpr is not a valid expression name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ A12345678".split(" "), m);
        String failStat = "A12345678 is not a valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecuteInt1() {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(-20, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt2() {
        Command c1 = new CommandUnexpr("unexpr exp2 # 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(20, c1.execute(m).getO());
    }

    @Test
    public void testExecuteBool() {
        Command c1 = new CommandUnexpr("unexpr exp2 ! false".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteFail1() {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ 20".split(" "), m);
        m.setExecuting(false);
        assertEquals(false, c1.execute(m).getO());
    }

    @Test
    public void testExecuteFail2() {
        Command c1 = new CommandUnexpr("unexpr exp2 ~ abc".split(" "), m);
        m.setExecuting(true);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :exp2 ExpRef1 is not a valid data";
        assertEquals(failStat ,outContent.toString().trim());
    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}