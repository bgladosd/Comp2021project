package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandVarDefTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCommandVarDefConstructor1() {
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        assertEquals("vardef1" ,c1.getLabel());
    }

    @Test
    public void testCommandVarDefConstructor2() {
        Command c1 = new CommandVarDef("vardef vardef1 int x 100 6thEle".split(" "), m);
        String failStat = "instruction failed! vardef statement should only have 5 elements which is (vardef lable type varName expRef)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testCommandVarDefConstructor3() {
        Command c1 = new CommandVarDef("vardef vardef int x abc".split(" "), m);
        String failStat = "instruction failed! vardef is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testCommandVarDefConstructor4() {
        Command c1 = new CommandVarDef("vardef vardef1 int x abc".split(" "), m);
        String failStat = "instruction failed! expRef is not valid value";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testCommandVarDefConstructor5() {
        Command c1 = new CommandVarDef("vardef vardef1 long x 100".split(" "), m);
        String failStat = "instruction failed! expRef is not type :long";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testCommandVarDefExecute1() {
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        c1.execute(m);
        assertEquals(100, m.getData("x").getO());
        assertEquals("int", m.getData("x").getType());
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}