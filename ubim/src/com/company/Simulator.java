package com.company;

import java.util.Collection;
import java.util.Random;

/**
 * Created by rami on 3/27/2016.
 */
public class Simulator implements ISimulator {
    private IDataStruct dataStruct;
    private final Object[] itemsPool;
    private Thread itemsAdderThread;
    private boolean isStop;

    public Simulator(Object[] itemsPool) {
        dataStruct = new DataStructImpl1();
        this.itemsPool = itemsPool;
        itemsAdderThread = new Thread(new ItemsAdder());
    }

    public Simulator(Object[] itemsPool, IDataStruct theDataStruct) {
        dataStruct = theDataStruct;
        this.itemsPool = itemsPool;
        itemsAdderThread = new Thread(new ItemsAdder());
    }

    private class ItemsAdder implements Runnable {
        @Override
        public void run() {
            while (!isStop) {
                int randomIndex = new Random().nextInt(itemsPool.length);
                Object randomItem = itemsPool[randomIndex];
                dataStruct.add(randomItem);
                dataStruct.printStatus();
                try {
                    Thread.sleep(1000); // TODO: remove this, For debug only.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void start() {
        System.out.println("Starting itemsAdderThread...");
        itemsAdderThread.start();
    }

    @Override
    public Collection<Object> stop() {
        System.out.println("Stopping itemsAdderThread...");
        isStop = true;
        return dataStruct.getMaxValues();
    }

}
