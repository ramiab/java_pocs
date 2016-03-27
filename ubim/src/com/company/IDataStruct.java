package com.company;

import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public interface IDataStruct {
    void add(Object item);

    boolean remove(Object item);

    Integer getValue(Object item);

    Set<Object> getMaxValues();

    void printStatus(); // This is just for debugging, it is not part of the requested interface.
}
