package com.mukit.objectClone;

public class Main {

	public static void main(String[] args) {
		
		Identity identity = new Identity("CSE", "09", 97);
		Person person1 = new Person("Mukit", "Chowdhury", 27, identity);
		try {
			Person person2 = (Person) person1.mukit();
			System.out.println("Name: " + person2.getFirstName() + " " + person2.getLastName());
			System.out.println("Batch: " + person2.getIdentity().batch + ", Department: " 
					+ person2.getIdentity().department + ", RollNumber: "
					+ person2.getIdentity().rollNumber);
			
			person1.identity.department = "EEE";
			person1.identity.rollNumber = 56;
			System.out.println("Batch: " + person2.getIdentity().batch + ", Department: " 
					+ person2.getIdentity().department + ", RollNumber: "
					+ person2.getIdentity().rollNumber);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
