package com.company;

import java.util.Collection;

/**
 * Created by rami on 3/27/2016.
 *
 * From the exercise instructions:
 * 1. add(item)    : Adds the item to the collection (with a value 1), or increments the value if it already exists in the collection.
 * 2. remove(item) : Removes the item from the collection. Returns true if and only if the item was found and removed.
 * 3. getValue(item) : Returns the current value of a given item.
 * 4. getMaxValues() : Returns the set of items having the maximum value.
 *
 */
public interface IDataStruct {

    void add(Object item);

    boolean remove(Object item);

    Integer getValue(Object item);

    Collection<Object> getMaxValues();

    void printStatus(); // This is just for debugging, it is not part of the requested interface.
}
