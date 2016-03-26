package com.company;

/**
 * Created by rami on 3/22/2016.
 */
public class Singleton1 {
    private static Singleton1 ourInstance = new Singleton1();

    public static Singleton1 getInstance() {
        return ourInstance;
    }

    private Singleton1() {
    }
}
