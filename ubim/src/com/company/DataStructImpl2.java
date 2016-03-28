package com.company;

import java.util.*;

/**
 * Created by rami on 3/27/2016.
 *
 * This IDataStruct implementaion is based on the following members:
 *      - itemsMap    - HashMap<Object, Integer>  of Item  --> Count .
 *
 * Algorithms:
 * ADD - item is added to the itemsMap. O(1).
 * REMOVE - removal from the itemsMap.  O(1).
 * GET_VALUE - simple access to the itemsMap. O(1).
 * GET_MAX_VALUES - iterate through itemsMap and find the items with max-count.
 *                  Through the iteration, keep a collection of the items with the highest count so far, add to the collection
 *                  any item that has this count.
 *                  If a higher count was found then the collection is dropped and a new highest-count-items collection
 *                  is started.
 *                  O(n).
 *
 * This implementation has lower memory footprint and add -> O(1), but getMaxValues takes O(n).
 *
 * Each DataStruct operation is documented with general complexity.
 * Complexity table that was referenced:
 * @see <a href="http://infotechgems.blogspot.co.il/2011/11/java-collections-performance-time.html">Java Collections – Performance (Time Complexity)</a>
 *
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
     * Complexity: O(1).
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
