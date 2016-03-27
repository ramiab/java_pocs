package com.company;

import java.util.*;

/**
 * Created by rami on 3/27/2016.
 *
 * @see <a href="http://infotechgems.blogspot.co.il/2011/11/java-collections-performance-time.html">Java Collections – Performance (Time Complexity)</a>
 *
 */
public class DataStructImpl1 implements IDataStruct {
    private Map<Object, Integer> itemsMap = new HashMap<>();
    private CountersMap countersMap = new CountersMap();

    /**
     * Complexity: O(1)+O(log n) = O(log n)
     *
     * @param item
     */
    @Override
    public void add(Object item) {
        Integer currentCount = itemsMap.get(item);
        int newCount = (currentCount == null) ? 1 : currentCount + 1;
        countersMap.add(newCount, item);
        itemsMap.put(item, newCount);
        System.out.printf("Item Added: %s ; count = %s\n", item, newCount);
    }

    /**
     * Complexity: O(1)+O(log n) = O(log n)
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
            return countersMap.remove(currentCount, item);
        }
    }

    /**
     * Complexity: O(1)
     *
     * @param item
     * @return
     */
    @Override
    public Integer getValue(Object item) {
        return itemsMap.get(item);
    }

    /**
     * Complexity: O(1)
     *
     * @return
     */
    @Override
    public Collection<Object> getMaxValues() {
        return countersMap.getMaxValues();
    }

    @Override
    public void printStatus() {
        System.out.println("================");
        System.out.println("itemsMap    = " + itemsMap);
        System.out.println("countersMap = \n" + countersMap);
        System.out.println("================");
    }

    private class CountersMap {
        private TreeMap<Integer, Set<Object>> innerCountersMap = new TreeMap<>();

        /**
         * Avg Complexity: O(log n)
         *
         * @param count
         * @param item
         */
        void add(int count, Object item) {
            Set<Object> itemsSet = innerCountersMap.get(count);
            if (null == innerCountersMap.get(count)) {
                itemsSet = new HashSet<>();
                itemsSet.add(item);
                innerCountersMap.put(count, itemsSet);
            } else {
                itemsSet.add(item);
            }
            innerRemove(count - 1, item);
        }

        /**
         * Avg Complexity: O(log n)
         *
         * @param count
         * @param item
         */
        boolean remove(int count, Object item) {
            Set<Object> itemsSet = innerCountersMap.get(count);
            assert itemsSet != null : "itemsSet doesn't exist for count " + count;
            return innerRemove(count, item);
        }

        private boolean innerRemove(int count, Object item) {
            boolean retVal = true;
            Set<Object> itemsSet = innerCountersMap.get(count);
            if (itemsSet != null) {
                retVal = itemsSet.remove(item);
                if (itemsSet.isEmpty())
                    innerCountersMap.remove(count);
            }

            return retVal;
        }

        /**
         * Avg Complexity: O(1)
         *
         * @return
         */
        Collection<Object> getMaxValues() {
            if(innerCountersMap.isEmpty())
                return null;
            Collection<Object> maxValues = innerCountersMap.lastEntry().getValue();
            assert maxValues != null : "Unexpected behavior: innerCountersMap has no data.";
            return maxValues;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Integer, Set<Object>> entry : innerCountersMap.entrySet()) {
                Integer key = entry.getKey();
                Set<Object> value = entry.getValue();
                sb.append("              ").append(key).append(" => ").append(value).append("\n");
            }
            return sb.toString();
        }

    }

}
