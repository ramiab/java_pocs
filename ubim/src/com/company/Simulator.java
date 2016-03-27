package com.company;

import java.util.Random;
import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public class Simulator implements ISimulator{
    private IMaxValueDS maxValueDS;
    private final String[] itemsPool;
    private Thread itemsAdderThread;
    private boolean isStop;

    public Simulator(String[] itemsPool) {
        maxValueDS = new MaxValueDSImpl();
        this.itemsPool = itemsPool;
        itemsAdderThread = new Thread(new ItemsAdder());
    }

    private class ItemsAdder implements Runnable {
        @Override
        public void run() {
            while (!isStop) {
                int randomIndex = new Random().nextInt(itemsPool.length);
                String randomItem = itemsPool[randomIndex];
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
    public Set<String> stop() {
        System.out.println("Stopping itemsAdderThread...");
        isStop = true;
        return maxValueDS.getMaxValues();
    }

}
