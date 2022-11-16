package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataObjectTest {

    Memory m = new Memory();
    Command command = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);

    @Test
    public void testConstructor1() {

        DataObject data = new DataObject("123", m);
        assertEquals(123, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testConstructor2() {
        DataObject data = new DataObject("true", m);
        assertEquals(true, data.getO());
        assertEquals("bool", data.getType());
    }

    @Test
    public void testConstructor3() {
        DataObject data = new DataObject("100000", m);
        assertEquals(99999, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void tesConstructor4() {
        DataObject data = new DataObject("-100000", m);
        assertEquals(-99999, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testConstructor5() {
        DataObject data = new DataObject("int", 123, m);
        assertEquals(123, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testConstructor6() {
        DataObject data = new DataObject("bool", false, m);
        assertEquals(false, data.getO());
        assertEquals("bool", data.getType());
    }

    @Test
    public void testConstructor7() {
        DataObject data = new DataObject("d6as56da7g7", m);
        assertFalse(data.autoSetData("d6as56da7g7", m));
        assertEquals(null, data.getO());
        assertEquals(null, data.getType());
    }

    @Test
    public void testConstructorAndExpression() {

        DataObject data = new DataObject(command, m);
        assertEquals(command, data.getO());
        assertEquals("e", data.getType());

        m.setExecuting(true);
        assertTrue(data.autoSetData("exp1", m));
        assertEquals(60, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testToString1() {
        DataObject data = new DataObject("123", m);
        assertEquals("123", data.toString());
    }

    @Test
    public void testToString2() {
        DataObject data = new DataObject("true", m);
        assertEquals("true", data.toString());
    }

    @Test
    public void testToString3() {
        DataObject data = new DataObject(command, m);
        m.setExecuting(true);
        assertEquals("60", data.toString());
    }

    @Test
    public void testSetMethod() {
        DataObject data = new DataObject("123", m);
        data.setO(true);
        data.setType("bool");
        assertEquals(true, data.getO());
        assertEquals("bool", data.getType());

        data.setInteger(321);
        assertEquals(321, data.getO());
        assertEquals("int", data.getType());

        data.setBoolean(false);
        assertEquals(false, data.getO());
        assertEquals("bool", data.getType());

    }
}