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

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMemoryGetAddPrintData () {
        Memory m = new Memory();
        DataObject dataX = new DataObject("123", m);
        DataObject dataB1 = new DataObject("true", m);
        m.addData("x", dataX);
        m.addData("b1", dataB1);

        assertEquals(dataX, m.getData("x"));
        assertEquals(dataB1, m.getData("b1"));
        assertNull(m.getData("unknown"));

        String [] printString = {"x : 123", "b1 : true"};
        m.printData();
        assertEquals(printString[0], outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1], outContent.toString().split("\n")[1].trim());
    }

    @Test
    public void testMemoryGetAddPrintCmd () {
        Memory m = new Memory();
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        //assertEquals("vardef1 added", outContent.toString().trim());

        Command c2 = new CommandPrint("print print1 x".split(" "), m);
        //check output
        String [] printString = {"vardef1 added", "print1 added"};
        assertEquals(printString[0], outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1], outContent.toString().split("\n")[1].trim());
        //m.addCmd("vardef1", c1);
        //m.addCmd("print1", c2);

        assertEquals(c1, m.getCmd("vardef1"));
        assertEquals(c2, m.getCmd("print1"));
        assertNull(m.getCmd("unkownCommand"));

        String [] printString2 = {"vardef1 : hk.edu.polyu.comp.comp2021.simple.model.CommandVarDef",
                "print1 : hk.edu.polyu.comp.comp2021.simple.model.CommandPrint"};
        m.printCmd();
        //assertTrue(Arrays.stream(printString2).anyMatch((outContent.toString().split("\n")[2].trim())::equals));
        String output1 = outContent.toString().split("\n")[3].trim();
        String output2 = outContent.toString().split("\n")[2].trim();
        assertEquals(printString2[0], output1.substring(0, output1.length()-9));
        assertEquals(printString2[1], output2.substring(0, output2.length()-9));
    }

    @Test
    public void testMemoryGetAddProgram () {
        Memory m = new Memory();
        Command c1 = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
        Command c2 = new CommandPrint("print print1 x".split(" "), m);
        Command c3 = new CommandProgram("program program1 print1".split(" "), m);
        m.addProgram(c3.getLabel(), c3);

        assertEquals(c3, m.getProgram("program1"));
    }

    @Test
    public void testMemorycheckIsValidExpression () {
        Memory m = new Memory();
        assertTrue(m.checkIsValidExpression("123"));
        assertTrue(m.checkIsValidExpression("true"));
        Command c1 = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        assertTrue(m.checkIsValidExpression("60"));
        assertFalse(m.checkIsValidExpression("A12345678"));
    }

    @Test
    public void testMemoryCheckIsValidNameOrLabel () {
        Memory m = new Memory();
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

    @Test
    public void testMemoryCheckIsValidProgramName () {
        Memory m = new Memory();
        assertTrue(m.checkIsValidProgramName("A1234567"));
        assertTrue(m.checkIsValidProgramName("Aaa123"));

        assertFalse(m.checkIsValidProgramName("A12345678910111213141516171819202122232425"));
        assertFalse(m.checkIsValidProgramName("^%$%&*`"));
        assertFalse(m.checkIsValidProgramName("01234567"));
        String[] Identifiers = { "int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "inspect" };
        for (String string : Identifiers) {
            assertFalse(m.checkIsValidProgramName(string));
        }
    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}