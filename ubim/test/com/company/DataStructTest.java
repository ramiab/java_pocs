package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rami on 3/27/2016.
 */
public class DataStructTest {
    private List<String> itemsList;
    IDataStruct dataStruct;

    public DataStructTest() {
        dataStruct = new DataStructImpl1();
    }

    @org.junit.Before
    public void setUp() throws Exception {
        itemsList = Arrays.asList("a", "b", "b", "c", "a");
    }

    @org.junit.Test
    public void add() throws Exception {
        for (String item : itemsList) {
            dataStruct.add(item);
            dataStruct.printStatus();
        }
        Collection<Object> maxValues = dataStruct.getMaxValues();
        System.out.println("maxValues = " + maxValues);

        assertEquals(2, maxValues.size());
        assertTrue(maxValues.contains("a"));
        assertTrue(maxValues.contains("b"));

    }

    @org.junit.Test
    public void remove() throws Exception {
        add(); // adding items

        dataStruct.remove("a");
        Collection<Object> maxValues = dataStruct.getMaxValues();
        System.out.println("maxValues = " + maxValues);

        assertEquals(1, maxValues.size());
        assertTrue(!maxValues.contains("a"));
        assertTrue(maxValues.contains("b"));

    }

    @org.junit.Test
    public void getValue() throws Exception {
        add(); // adding items

        assertEquals(2, dataStruct.getValue("a").intValue());
        assertEquals(1, dataStruct.getValue("c").intValue());

        dataStruct.remove("a");
        assertNull(dataStruct.getValue("a"));
    }

    @org.junit.Test
    public void getMaxValues() throws Exception {
        add(); // adding items

        Collection<String> expectedMaxValues = new HashSet<>(Arrays.asList("a", "b"));
        assertEquals(expectedMaxValues, dataStruct.getMaxValues());
    }

    //    @org.junit.Test
    private void simulatorCycle(Object[] itemsPool) throws Exception {
        Simulator simulator = new Simulator(itemsPool, dataStruct);

        simulator.start();
        Thread.sleep(10 * 1000);
        Collection<Object> maxValuesOnTermination = simulator.stop();

        System.out.println("maxValuesOnTermination = " + maxValuesOnTermination);

        assertTrue(!maxValuesOnTermination.isEmpty());
        for (Object item : maxValuesOnTermination) {
            assertTrue(Arrays.asList(itemsPool).contains(item));
        }
    }

    @org.junit.Test
    public void simulatorWithIntegers() throws Exception {
        Integer[] itemsPool = {1000, 2000, 3000, 4000, 5000};
        simulatorCycle(itemsPool);
    }

    @org.junit.Test
    public void simulatorWithStrings() throws Exception {
        String[] itemsPool = "abcdef".split("");
        simulatorCycle(itemsPool);
    }

}