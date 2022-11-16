package hk.edu.polyu.comp.comp2021.simple.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataObjectTest {

    @Test
    public void testDataObjectConstructor1() {
        Memory m = new Memory();
        DataObject data = new DataObject("123", m);
        assertEquals(123, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testDataObjectConstructor2() {
        Memory m = new Memory();
        DataObject data = new DataObject("true", m);
        assertEquals(true, data.getO());
        assertEquals("bool", data.getType());
    }

    @Test
    public void testDataObjectConstructor3() {
        Memory m = new Memory();
        DataObject data = new DataObject("100000", m);
        assertEquals(99999, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testDataObjectConstructor4() {
        Memory m = new Memory();
        DataObject data = new DataObject("-100000", m);
        assertEquals(-99999, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testDataObjectConstructor5() {
        Memory m = new Memory();
        DataObject data = new DataObject("int", 123, m);
        assertEquals(123, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testDataObjectConstructor6() {
        Memory m = new Memory();
        DataObject data = new DataObject("bool", false, m);
        assertEquals(false, data.getO());
        assertEquals("bool", data.getType());
    }

    @Test
    public void testDataObjectConstructorAndExpression() {
        Memory m = new Memory();
        Command command = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        DataObject data = new DataObject(command, m);
        assertEquals(command, data.getO());
        assertEquals("e", data.getType());

        m.setExecuting(true);
        assertTrue(data.autoSetData("exp1", m));
        assertEquals(60, data.getO());
        assertEquals("int", data.getType());
    }

    @Test
    public void testDataObjectToString1() {
        Memory m = new Memory();
        DataObject data = new DataObject("123", m);
        assertEquals("123", data.toString());
    }

    @Test
    public void testDataObjectToString2() {
        Memory m = new Memory();
        DataObject data = new DataObject("true", m);
        assertEquals("true", data.toString());
    }

    @Test
    public void testDataObjectToString3() {
        Memory m = new Memory();
        Command command = new CommandBinexpr("binexpr exp1 3 * 20".split(" "), m);
        DataObject data = new DataObject(command, m);
        m.setExecuting(true);
        assertEquals("60", data.toString());
    }
}