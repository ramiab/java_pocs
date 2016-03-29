package com.company;

/**
 * Created by rami on 3/29/2016.
 */
public class Notifier implements Runnable {

    private final Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        try {
            Thread.sleep(10*1000);
            synchronized (msg) {
                msg.setMsg(name + " Notifier work done");
//                msg.notify();
                 msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
