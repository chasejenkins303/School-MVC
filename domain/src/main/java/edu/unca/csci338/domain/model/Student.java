package edu.unca.csci338.domain.model;

import java.util.ArrayList;
import java.util.List;


public class Student extends Person{
	
	private int hoursCompleted;
	private int hoursCurrent;
	private int hoursNeeded;
//	public enum status{
//		APPLIED,
//		ENROLLED,
//		WITHDRAWN
//	}
	private String status;
	//private status sta;
	public int yearLevel;
	private List<Course> courses;
	//TODO: need variable for schedule
	
	public Student(int ID, String FN, String LN, int hoursComp, int hoursCurr, int hoursNeed, int yearLevel, String status) {
		super(ID, FN, LN);
		this.hoursCompleted=hoursComp;
		this.hoursCurrent=hoursCurr;
		this.hoursNeeded=hoursNeed;
		this.yearLevel=yearLevel;
		this.status=status;
		
		//this.status=status;
		this.setCourses(courses);
	}
	
	public Student(String FN, String LN, int hoursComp, int hoursCurr, int hoursNeed, int yearLevel, String status) {
		super(FN, LN);
		this.hoursCompleted=hoursComp;
		this.hoursCurrent=hoursCurr;
		this.hoursNeeded=hoursNeed;
		this.yearLevel=yearLevel;
		this.status=status;
		
		
		//this.status=status;
		this.setCourses(courses);
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getYearLevel() {
		return yearLevel;
	}

	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}

	public int getHoursCompleted() {
		return hoursCompleted;
	}

	public void setHoursCompleted(int hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}

	public int getHoursCurrent() {
		return hoursCurrent;
	}

	public void setHoursCurrent(int hoursCurrent) {
		this.hoursCurrent = hoursCurrent;
	}

	public int getHoursNeeded() {
		return hoursNeeded;
	}

	public void setHoursNeeded(int hoursNeeded) {
		this.hoursNeeded = hoursNeeded;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course c) {
		courses.add(c);
	}
	

	

}
