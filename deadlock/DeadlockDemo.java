package com.mukit.deadlock;

public class DeadlockDemo {
    public static void main(String[] args) {
        SharedData shared1 = new SharedData();
        SharedData shared2 = new SharedData();
        Thread1 thread1 = new Thread1(shared1, shared2);
        Thread2 thread2 = new Thread2(shared1, shared2);

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);

        t1.start();
        t2.start();
    }
}


