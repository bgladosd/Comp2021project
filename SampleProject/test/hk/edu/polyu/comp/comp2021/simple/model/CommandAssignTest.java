package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandAssignTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cv2 = new CommandVarDef("vardef vardef2 bool y false".split(" "), m);


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
        cv2.execute(m);
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandAssign("assign assign1 x 5".split(" "), m);
        assertEquals(c1 ,m.getCmd("assign1"));
        assertEquals("assign1", m.getCmd("assign1").getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandAssign("assign assign1 x 5 5thEle".split(" "), m);
        String failStat = "instruction failed! assign statement should only have 4 elements which is (assign lab varName expRef)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandAssign("assign assign x 5".split(" "), m);
        String failStat = "assign is not a valid label name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandAssign("assign assign1 A12345678 5".split(" "), m);
        String failStat = "A12345678 is not a valid variable name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor5 () {
        Command c1 = new CommandAssign("assign assign1 x A12345678".split(" "), m);
        String failStat = "A12345678 is not a valid Expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecute() {
        Command c1 = new CommandAssign("assign assign1 x 5".split(" "), m);
        c1.execute(m);
        assertEquals(5, m.getData("x").getO());
    }

    @Test
    public void testExecute2() {
        Command c1 = new CommandAssign("assign assign1 y true".split(" "), m);
        c1.execute(m);
        assertEquals(true, m.getData("y").getO());
    }

    @Test
    public void testExecuteFail1() {
        Command c1 = new CommandAssign("assign assign1 z 5".split(" "), m);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :assign1 z not found";
        assertEquals(failStat ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testExecuteFail2() {
        Command c1 = new CommandAssign("assign assign1 x z".split(" "), m);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :assign1 ExpRef is not a valid data";
        assertEquals(failStat ,outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testExecuteFail3() {
        Command c1 = new CommandAssign("assign assign1 x y".split(" "), m);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :assign1 can't assign if x and y are not the same data type";
        assertEquals(failStat ,outContent.toString().split("\n")[1].trim());
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}