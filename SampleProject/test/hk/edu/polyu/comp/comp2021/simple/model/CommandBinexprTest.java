package hk.edu.polyu.comp.comp2021.simple.model;

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
    }

    
}