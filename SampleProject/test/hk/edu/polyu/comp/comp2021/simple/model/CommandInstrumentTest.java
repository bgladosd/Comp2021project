package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandInstrumentTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command ca = new CommandAssign("assign assign1 x 300".split(" "), m);
    Command cb = new CommandBlock("block block1 vardef1 print1 assign1 print1".split(" "), m);
    Command cpg = new CommandProgram("program program1 block1".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testConstructorExecute () {
        m.setRunningProgramName("program1");
        Command c1 = new CommandInstrument("instrument program1 print1 before 0".split(" "), m);
        String[] printString = {"Instructment : program1 print1 before 0 added", "{0}", "[100]", "{0}", "[300]"};
        Command ce = new CommandExecute("execute program1".split(" "), m);
        assertEquals(printString[0] ,outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1] ,outContent.toString().split("\n")[1].trim());
        assertEquals(printString[2] ,outContent.toString().split("\n")[2].trim());
        assertEquals(printString[3] ,outContent.toString().split("\n")[3].trim());
        assertEquals(printString[4] ,outContent.toString().split("\n")[4].trim());
    }

    @Test
    public void testConstructorExecute2 () {
        m.setRunningProgramName("program1");
        Command c1 = new CommandInstrument("instrument program1 print1 after 0".split(" "), m);
        String[] printString = {"Instructment : program1 print1 after 0 added", "[100]", "{0}", "[300]" , "{0}"};
        Command ce = new CommandExecute("execute program1".split(" "), m);
        assertEquals(printString[0] ,outContent.toString().split("\n")[0].trim());
        assertEquals(printString[1] ,outContent.toString().split("\n")[1].trim());
        assertEquals(printString[2] ,outContent.toString().split("\n")[2].trim());
        assertEquals(printString[3] ,outContent.toString().split("\n")[3].trim());
        assertEquals(printString[4] ,outContent.toString().split("\n")[4].trim());
    }

    @Test
    public void testConstructorExecute3 () {
        m.setRunningProgramName("program1");
        Command c1 = new CommandInstrument("instrument program1 print1 before 0 6thEle".split(" "), m);
        String printString = "instruction failed! instrument statement should only have 5 elements which is (instrument programName statementLab pos expRef)";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute4 () {
        Command c1 = new CommandInstrument("instrument program2 print1 before 0".split(" "), m);
        String printString = "program2 : no such program";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute5 () {
        Command c1 = new CommandInstrument("instrument program1 print before 0".split(" "), m);
        String printString = "print is not a valid label";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute6 () {
        Command c1 = new CommandInstrument("instrument program1 print1 before 1abc".split(" "), m);
        String printString = "1abc is not a valid expression";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute7 () {
        Command c1 = new CommandInstrument("instrument program1 print1 between 0".split(" "), m);
        String printString = "pos should only be 'before' or 'after'";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute8 () {
        Command c1 = new CommandInstrument("instrument program1 print1 after y".split(" "), m);
        String printString = "instructment can't find the variable with var name : y";
        Command ce = new CommandExecute("execute program1".split(" "), m);
        assertEquals(printString ,outContent.toString().split("\n")[2].trim());
        assertEquals(printString ,outContent.toString().split("\n")[4].trim());
    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}