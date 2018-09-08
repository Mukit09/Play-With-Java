package com.mukit.threadSynchronize;

public class Main {

	public static void main(String[] args) {
		
		Printer printer = new Printer();
		MyRunnable runnable = new MyRunnable(printer);
		Thread t1 = new Thread(runnable);
		t1.setName("Thread1");
		t1.start();
		
		MyRunnable runnable2 = new MyRunnable(printer);
		Thread t2 = new Thread(runnable2);
		t2.setName("Thread2");
		t2.start();
	}

}
