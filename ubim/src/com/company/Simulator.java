package com.company;

import java.util.Random;
import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public class Simulator implements ISimulator {
    private IDataStruct maxValueDS;
    private final Object[] itemsPool;
    private Thread itemsAdderThread;
    private boolean isStop;

    public Simulator(Object[] itemsPool) {
        maxValueDS = new DataStructImpl1();
        this.itemsPool = itemsPool;
        itemsAdderThread = new Thread(new ItemsAdder());
    }

    private class ItemsAdder implements Runnable {
        @Override
        public void run() {
            while (!isStop) {
                int randomIndex = new Random().nextInt(itemsPool.length);
                Object randomItem = itemsPool[randomIndex];
                maxValueDS.add(randomItem);
                maxValueDS.printStatus();
                try {
                    Thread.sleep(1000); // TODO: remove this
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
    public Set<Object> stop() {
        System.out.println("Stopping itemsAdderThread...");
        isStop = true;
        return maxValueDS.getMaxValues();
    }

}
