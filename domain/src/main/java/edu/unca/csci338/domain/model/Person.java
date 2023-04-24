package edu.unca.csci338.domain.model;

public class Person {
	
	private int ID;
	private String firstName;
	private String lastName;
		
	public Person(int iD, String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = iD;
		
	}
	
	public Person(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public Person() {
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 

}

