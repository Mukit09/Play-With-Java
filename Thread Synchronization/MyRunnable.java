package com.mukit.threadSynchronize;

public class MyRunnable implements Runnable{

	Printer printer;
	int x;
	
	public MyRunnable(Printer printer) {

		this.printer = printer;
	}
	@Override
	public void run() {

		synchronized(printer) {
			printer.print();
		}
	}

}
