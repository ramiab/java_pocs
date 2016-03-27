package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by rami on 3/27/2016.
 */
public class DataStructTest {
    protected List<String> itemsList;
    protected IDataStruct dataStruct;

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
        Set<Object> maxValues = dataStruct.getMaxValues();
        System.out.println("maxValues = " + maxValues);

        assertEquals(2, maxValues.size());
        assertTrue(maxValues.contains("a"));
        assertTrue(maxValues.contains("b"));

    }

    @org.junit.Test
    public void remove() throws Exception {
        add(); // adding items

        dataStruct.remove("a");
        Set<Object> maxValues = dataStruct.getMaxValues();
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

        Set<String> expectedMaxValues = new HashSet<>(Arrays.asList("a", "b"));
        assertEquals(expectedMaxValues, dataStruct.getMaxValues());
    }
}