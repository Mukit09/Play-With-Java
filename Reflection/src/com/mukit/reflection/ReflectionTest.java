package com.mukit.reflection;

public class ReflectionTest {
	
	private String s;
	public ReflectionTest() {
		
		s = "Mukit09";
	}
	
	public void testMethod(int n, double t) {
		
		System.out.println("testMethod is called for " + n + " and " + t);
		return;
	}
	
	public void testMethod1() {
		
		System.out.println("String value is: " + s);
		return;
	}
}
