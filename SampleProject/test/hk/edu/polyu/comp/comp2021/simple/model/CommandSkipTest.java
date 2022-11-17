package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandSkipTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandSkip("skip skip1".split(" "), m);
        assertEquals(c1 ,m.getCmd("skip1"));
        assertEquals("skip1", m.getCmd("skip1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandSkip("skip skip1 3rdEle".split(" "), m);
        String failStat = "instruction failed! skip statement should only have 2 elements which is (skip skip1)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandSkip("skip skip".split(" "), m);
        String failStat = "skip is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute1() {
        Command c1 = new CommandSkip("skip skip1".split(" "), m);
        assertNull(c1.execute(m));
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}