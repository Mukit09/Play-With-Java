package com.mukit.deadlock;

public class SharedData {
    public synchronized void test1(SharedData shared) {
        System.out.println("Test1 called");
        shared.test2(this);
        System.out.println("Test1 ended");
    }

    public synchronized void test2(SharedData shared) {
        System.out.println("Test2 called");
        shared.test1(this);
        System.out.println("Test2 ended");
    }
}
