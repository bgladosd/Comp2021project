package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandBuilderTest {
    Memory m = new Memory();
    CommandBuilder cb = new CommandBuilder();
    String c;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void TestCommand1() {
        c = "vardef vardef1 int x 100";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("vardef1").getCmdString());
    }

    @Test
    public void TestCommand2() {
        c = "binexpr exp1 1 + 1";
        cb.buildCommand(c, m);
        assertNotNull(m.getData("exp1"));
    }

    @Test
    public void TestCommand3() {
        c = "unexpr exp2 ~ 1";
        cb.buildCommand(c, m);
        assertNotNull(m.getData("exp2"));
    }

    @Test
    public void TestCommand4() {
        c = "assign assign1 x 999";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("assign1").getCmdString());
    }

    @Test
    public void TestCommand5() {
        c = "print print1 x";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("print1").getCmdString());
    }

    @Test
    public void TestCommand6() {
        c = "skip skip1";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("skip1").getCmdString());
    }

    @Test
    public void TestCommand7() {
        c = "if if1 exp1 print1 print2";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("if1").getCmdString());
    }

    @Test
    public void TestCommand8() {
        c = "while while1 true block1";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("while1").getCmdString());
    }

    @Test
    public void TestCommand9() {
        c = "block block1 print1";
        cb.buildCommand(c, m);
        assertEquals(c, m.getCmd("block1").getCmdString());
    }

    @Test
    public void TestCommand10() {
        c = "program program1 block1";
        cb.buildCommand(c, m);
        assertEquals(c, m.getProgram("program1").getCmdString());
    }

    @Test
    public void TestCommandFail() {
        c = "Hello world!";
        cb.buildCommand(c, m);
        String failStat = "Command not found : Hello";
        assertEquals(failStat ,outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}