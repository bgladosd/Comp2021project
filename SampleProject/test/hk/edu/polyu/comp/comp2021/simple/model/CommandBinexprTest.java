package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandBinexprTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testConstructor1 () {
        Command c1 = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        assertEquals(c1 ,m.getData("exp1").getO());
        assertEquals("e", m.getData("exp1").getType());
        assertNull(c1.getLabel());
    }

    @Test
    public void testConstructor2 () {
        Command c1 = new CommandBinexpr("binexpr exp1 3 * 20 6thEle".split(" "), m);
        String failStat = "instruction failed! binexpr statement should only have 5 elements which is (binexpr expName expRef1 bop expRef2)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor3 () {
        Command c1 = new CommandBinexpr("binexpr binexpr 3 * 20".split(" "), m);
        String failStat = "binexpr is not a valid expression name";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor4 () {
        Command c1 = new CommandBinexpr("binexpr exp1 A12345678 * 20".split(" "), m);
        String failStat = "A12345678 is not a valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructor5() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 * A12345678".split(" "), m);
        String failStat = "A12345678 is not a valid expression";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecuteInt1() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 + 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(23, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt2() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 - 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(-17, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt3() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(60, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt4() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 / 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(0, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt5() {
        Command c1 = new CommandBinexpr("binexpr exp1 3 % 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(3, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt6() {
        Command c1 = new CommandBinexpr("binexpr exp1 21 > 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt7() {
        Command c1 = new CommandBinexpr("binexpr exp1 20 >= 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt8() {
        Command c1 = new CommandBinexpr("binexpr exp1 19 < 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt9() {
        Command c1 = new CommandBinexpr("binexpr exp1 20 <= 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt10() {
        Command c1 = new CommandBinexpr("binexpr exp1 20 == 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteInt11() {
        Command c1 = new CommandBinexpr("binexpr exp1 1231 != 20".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteBool1() {
        Command c1 = new CommandBinexpr("binexpr exp1 true == true".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteBool2() {
        Command c1 = new CommandBinexpr("binexpr exp1 true != false".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteBool3() {
        Command c1 = new CommandBinexpr("binexpr exp1 true || false".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteBool4() {
        Command c1 = new CommandBinexpr("binexpr exp1 true && true".split(" "), m);
        m.setExecuting(true);
        assertEquals(true, c1.execute(m).getO());
    }

    @Test
    public void testExecuteFail1() {
        Command c1 = new CommandBinexpr("binexpr exp1 1 + 1".split(" "), m);
        m.setExecuting(false);
        assertEquals(false, c1.execute(m).getO());
    }

    @Test
    public void testExecuteFail2() {
        Command c1 = new CommandBinexpr("binexpr exp1 abc + 1".split(" "), m);
        m.setExecuting(true);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :exp1 ExpRef1 is not a valid data";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecuteFail3() {
        Command c1 = new CommandBinexpr("binexpr exp1 1 + abc".split(" "), m);
        m.setExecuting(true);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :exp1 ExpRef2 is not a valid data";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testExecuteFail4() {
        Command c1 = new CommandBinexpr("binexpr exp1 true + 1".split(" "), m);
        m.setExecuting(true);
        assertEquals(false, c1.execute(m).getO());
        String failStat = "Error on :exp1 can't do calculation if ExpRef1 and ExpRef2 are not the same type";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


}