package com.mukit.objectClone;

public class Person implements Cloneable{
	
	String firstName;
	String lastName ;
	int age;
	Identity identity;
	
	Person(String firstName, String lastName, int age, Identity identity) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.identity = identity;
	}
	
	public int getAge() {	
		return age;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Identity getIdentity() {
		return identity;
	}
	
	public Object mukit() throws CloneNotSupportedException {
		return super.clone();
	}
}