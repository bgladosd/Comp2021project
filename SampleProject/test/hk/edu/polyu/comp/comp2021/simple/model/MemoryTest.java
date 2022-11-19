package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.Assert.*;

public class MemoryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGetAddPrintData () {
        DataObject dataX = new DataObject("123", m);
        DataObject dataB1 = new DataObject("true", m);
        m.addData("x", dataX);
        m.addData("b1", dataB1);

        assertEquals(dataX, m.getData("x"));
        assertEquals(dataB1, m.getData("b1"));
        assertNull(m.getData("unknown"));
    }

    @Test
    public void testGetAddPrintCmd () {
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        Command c2 = new CommandPrint("print print1 x".split(" "), m);
        assertEquals(c1, m.getCmd("vardef1"));
        assertEquals(c2, m.getCmd("print1"));
        assertNull(m.getCmd("unkownCommand"));
    }

    @Test
    public void testGetAddProgram () {
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        Command c2 = new CommandPrint("print print1 x".split(" "), m);
        Command c3 = new CommandProgram("program program1 print1".split(" "), m);
        m.addProgram(c3.getLabel(), c3);

        assertEquals(c3, m.getProgram("program1"));
    }

    @Test
    public void testcheckIsValidExpression () {
        assertTrue(m.checkIsValidExpression("123"));
        assertTrue(m.checkIsValidExpression("true"));
        Command cb = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        assertTrue(m.checkIsValidExpression("60"));
        assertFalse(m.checkIsValidExpression("A12345678"));
    }

    @Test
    public void testCheckIsValidNameOrLabel () {
        assertTrue(m.checkIsValidNameOrLabel("A1234567"));
        assertTrue(m.checkIsValidNameOrLabel("Aaa123"));

        assertFalse(m.checkIsValidNameOrLabel("A12345678"));
        assertFalse(m.checkIsValidNameOrLabel("^%$%&*`"));
        assertFalse(m.checkIsValidNameOrLabel("01234567"));
        String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect" };
        for (String string : Identifiers) {
            assertFalse(m.checkIsValidNameOrLabel(string));
        }
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}