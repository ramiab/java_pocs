package com.company;

import java.util.*;

/**
 * Created by rami on 3/27/2016.
 */
public class MaxValueDSImpl implements IMaxValueDS {
    private Map <String, Integer> itemsMap = new HashMap<>();
    private CountersMap countersMap = new CountersMap();

    @Override
    public void add(String item) {
        Integer currentCount = itemsMap.get(item);
        int newCount = (currentCount==null) ? 1 : currentCount+1;
        countersMap.add(newCount, item);
        itemsMap.put(item, newCount);
        System.out.println("Item Added: "+item);
    }

    @Override
    public boolean remove(String item) {
        Integer currentCount = itemsMap.remove(item);
        if(currentCount==null) {
            System.out.println("Item not found, not Removed: "+item);
            return false;
        }
        else {
            System.out.println("Item Removed: "+item);
            return countersMap.remove(currentCount, item);
        }
    }

    @Override
    public int getValue(String item) {
        return itemsMap.get(item);
    }

    @Override
    public Set<String> getMaxValues() {
        return countersMap.getMaxValues();
    }

    @Override
    public void printStatus() {
        System.out.println("================");
        System.out.println("itemsMap    = "+itemsMap);
        System.out.println("countersMap = \n"+countersMap);
        System.out.println("================");
    }

    private class CountersMap {
        private TreeMap<Integer, Set<String>> innerCountersMap = new TreeMap<>();

        void add(int count, String item) {
            Set<String> itemsSet = innerCountersMap.get(count);
            if( null == innerCountersMap.get(count) ) {
                itemsSet = new HashSet<>();
                itemsSet.add(item);
                innerCountersMap.put(count, itemsSet);
            }
            else {
                itemsSet.add(item);
            }
            innerRemove(count-1, item);

        }

        boolean remove(int count, String item) {
            Set<String> itemsSet = innerCountersMap.get(count);
            assert itemsSet!=null : "itemsSet doesn't exist for count " + count;
            return innerRemove(count, item);
        }

        private boolean innerRemove(int count, String item) {
            Set<String> itemsSet = innerCountersMap.get(count);
            if (itemsSet != null) {
                itemsSet.remove(item);
                if(itemsSet.isEmpty())
                    innerCountersMap.remove(count);
            }

            return true;
        }

        Set<String> getMaxValues() {
            Set<String> itemsSet = innerCountersMap.lastEntry().getValue();
            assert itemsSet!=null : "innerCountersMap has no data";
            return itemsSet;
        }
        
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Integer, Set<String>> entry : innerCountersMap.entrySet()) {
                Integer key = entry.getKey();
                Set<String> value = entry.getValue();
                sb.append("              ").append(key).append(" => ").append(value).append("\n");
            }
            return sb.toString();
        }

    }

}
