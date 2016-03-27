package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by rami on 3/27/2016.
 */
public class DataStructImpl1Test {
    private List<String> itemsList;
    private DataStructImpl1 maxValueDSImpl;

    @org.junit.Before
    public void setUp() throws Exception {
        itemsList = Arrays.asList("a", "b", "b", "c", "a");
        maxValueDSImpl = new DataStructImpl1();
    }

    @org.junit.Test
    public void add() throws Exception {
        for (String item:itemsList) {
            maxValueDSImpl.add(item);
            maxValueDSImpl.printStatus();
        }
        Set<String> maxValues = maxValueDSImpl.getMaxValues();
        System.out.println("maxValues = "+maxValues);

        assertEquals(2, maxValues.size());
        assertTrue(maxValues.contains("a"));
        assertTrue(maxValues.contains("b"));

    }

    @org.junit.Test
    public void remove() throws Exception {
        add(); // adding items

        maxValueDSImpl.remove("a");
        Set<String> maxValues = maxValueDSImpl.getMaxValues();
        System.out.println("maxValues = "+maxValues);

        assertEquals(1, maxValues.size());
        assertTrue(!maxValues.contains("a"));
        assertTrue(maxValues.contains("b"));

    }

    @org.junit.Test
    public void getValue() throws Exception {
        add(); // adding items

        assertEquals(2, maxValueDSImpl.getValue("a").intValue());
        assertEquals(1, maxValueDSImpl.getValue("c").intValue());

        maxValueDSImpl.remove("a");
        assertNull(maxValueDSImpl.getValue("a"));
    }

    @org.junit.Test
    public void getMaxValues() throws Exception {
        add(); // adding items

        Set<String> expectedMaxValues = new HashSet<>(Arrays.asList("a", "b"));
        assertEquals(expectedMaxValues, maxValueDSImpl.getMaxValues());
    }
}