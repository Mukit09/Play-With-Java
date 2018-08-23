package com.mukit.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {

		ReflectionTest reflection = new ReflectionTest();
		Class c = reflection.getClass();
		System.out.println("The name of Class is: " + c.getName());
		
		try {
			Constructor constructor = c.getConstructor();
			System.out.println("The name of C is: " + constructor.getName());
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		System.out.println("Public Methods of Class are: ");
		Method[] methods = c.getMethods();
		
		for(Method method:methods) {
			System.out.println(method.getName());
		}
		try {
			Method method = c.getDeclaredMethod("testMethod", int.class, double.class);
			method.invoke(reflection, 10, 4);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		try {
			Field field = c.getDeclaredField("s");
			field.setAccessible(true);
			field.set(reflection, "Java");
			
			Method method = c.getDeclaredMethod("testMethod1");
			method.invoke(reflection);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
