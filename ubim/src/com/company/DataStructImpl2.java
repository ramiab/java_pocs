package com.company;

import java.util.*;

/**
 * Created by rami on 3/27/2016.
 */
public class DataStructImpl2 implements IDataStruct {
    Map<Object, Integer> itemsMap = new HashMap<>();

    /**
     * Avg. Complexity: O(1)
     *
     * @param item
     */
    @Override
    public void add(Object item) {
        Integer currentCount = itemsMap.get(item);
        int newCount = (currentCount == null) ? 1 : currentCount + 1;
        itemsMap.put(item, newCount);
        System.out.printf("Item Added: %s ; count = %s\n", item, newCount);
    }

    /**
     * Avg. Complexity: O(1)
     *
     * @param item
     * @return
     */
    @Override
    public boolean remove(Object item) {
        Integer currentCount = itemsMap.remove(item);
        if (currentCount == null) {
            System.out.println("Item not found, not Removed: " + item);
            return false;
        } else {
            System.out.println("Item Removed: " + item);
            return true;
        }
    }

    /**
     * Avg,Worse Complexity: O(1),O(n) respectively - If we have good hashing and lots of memory.
     *
     * @param item
     * @return
     */
    @Override
    public Integer getValue(Object item) {
        return itemsMap.get(item);
    }

    /**
     * Complexity: O(n)
     *
     * @return
     */
    @Override
    public Collection<Object> getMaxValues() {
        Collection<Object> retMaxValues = null;
        int currentMaxCount = 0;
        for (Map.Entry<Object, Integer> entry : itemsMap.entrySet()) {
            Integer count = entry.getValue();
            if( count > currentMaxCount ) {
                currentMaxCount = count;
                retMaxValues = new HashSet<>();
                retMaxValues.add( entry.getKey() );
            }
            else if ( count == currentMaxCount && retMaxValues != null ) {
                retMaxValues.add( entry.getKey() );
            }
        }

        return retMaxValues;
    }

    @Override
    public void printStatus() {
        System.out.println("================");
        System.out.println("itemsMap    = " + itemsMap);
        System.out.println("================");
    }
}
