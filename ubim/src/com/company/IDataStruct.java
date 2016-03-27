package com.company;

import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public interface IDataStruct {
    void add(String item);
    boolean remove(String item);
    Integer getValue(String item);
    Set<String> getMaxValues();

    void printStatus(); // This is just for debugging, it is not part of the requested interface.
}
