package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandExecuteTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Memory m = new Memory();
    Command cv = new CommandVarDef("vardef vardef1 int x 100".split(" "), m);
    Command cp = new CommandPrint("print print1 x".split(" "), m);
    Command cb = new CommandBlock("block block1 vardef1 print1".split(" "), m);
    Command cpg = new CommandProgram("program program1 block1".split(" "), m);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        cv.execute(m);
    }

    @Test
    public void testConstructorExecute () {

        Command c1 = new CommandExecute("execute program1".split(" "), m);
        String printString = "[100]";
        assertEquals(printString ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute2 () {

        Command c1 = new CommandExecute("execute program1 3rdEle".split(" "), m);
        String failStat = "instruction failed! execute statement should only have 2 elements which are (execute program1)";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @Test
    public void testConstructorExecute3 () {

        Command c1 = new CommandExecute("execute program99".split(" "), m);
        String failStat = "Program : program99 can not be found";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}