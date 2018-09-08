package com.mukit.threadSynchronize;

public class Printer {
	
	public void print() {
		
	//	synchronized (this) { // can also be done this using *this*
			
			for(int i = 0; i<10; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			System.out.println("");
	//	}
	}
}
