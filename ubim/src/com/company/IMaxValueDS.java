package com.company;

import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public interface IMaxValueDS {
    void add(String item);
    boolean remove(String item);
    int getValue(String item);
    Set<String> getMaxValues();

    void printStatus(); // This is just for debugging, it is not part of the requested interface.
}
