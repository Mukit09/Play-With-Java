package com.mukit.deadlock;

public class Thread1 implements  Runnable {

    private SharedData shared1;
    private SharedData shared2;

    public Thread1(SharedData shared1, SharedData shared2) {
        this.shared1 = shared1;
        this.shared2 = shared2;
    }

    @Override
    public void run() {
        shared1.test1(shared2);
    }
}