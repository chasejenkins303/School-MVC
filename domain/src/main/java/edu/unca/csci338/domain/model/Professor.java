package edu.unca.csci338.domain.model;

import edu.unca.csci338.domain.data.*;

public class Professor extends Person implements IStudentDataChangedEvent{
	
	private int iD;
	private String firstName;
	private String lastName;
	private int department;
	private boolean head;
	 
	private ProfessorStatus profStatus;
	

	public Professor(int iD, String firstName, String lastName, int department, String status, boolean head) {
		super(iD, firstName, lastName);
		this.department = department;
		this.profStatus = ProfessorStatus.fromString(status);
		this.head = head;
		StudentData.AddOnStudentDataChangeEventListner(this);
	}
	public Professor( String firstName, String lastName, int department2) {
		super(firstName, lastName);
		this.department = department2;
	}

	public Professor() {
		super();
	}


//	public String getFirstName() {
//		return firstName;
//	}
//
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//
//	public String getLastName() {
//		return lastName;
//	}
//
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}


	public int getDepartment() {
		return department;
	}


	public void setDepartment(int department) {
		this.department = department;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public String getStatus() {
		return profStatus.toString();
	}
	
	public boolean equals(Professor professor) {
		if(this.iD == professor.getID()) {
			return true;
		}
		return false;
	}
	
	public boolean getHead() {
		return head;
	}
	
	public void setHead(boolean head) {
		this.head = head;
	}
	@Override
	public void onStudentDataChanged(Student student) {
		// TODO Auto-generated method stub
		
	}
	

}

