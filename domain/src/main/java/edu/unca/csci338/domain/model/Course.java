package edu.unca.csci338.domain.model;



public class Course {

	private String nameOfCourse;
	private int courseReferenceNumber;
	private String description;
	private int departmentId;
	private int id;
	

	public Course(String nameOfCourse, int courseReferenceNumber, String description, int departmentId, int id) {
		this.nameOfCourse = nameOfCourse;
		this.courseReferenceNumber = courseReferenceNumber;
		this.description=description;
		this.departmentId=departmentId;
		this.setId(id);
	}
	
	public Course(String nameOfCourse, int courseReferenceNumber, String description, int departmentId) {
		this.nameOfCourse = nameOfCourse;
		this.courseReferenceNumber = courseReferenceNumber;
		this.description=description;
		this.departmentId=departmentId;
	}

	public int getDepartment() {
		return departmentId;
	}

	public void setDepartment(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameOfCourse() {
		return nameOfCourse;
	}

	public void setNameOfCourse(String nameOfCourse) {
		this.nameOfCourse = nameOfCourse;
	}

	public int getCourseReferenceNumber() {
		return courseReferenceNumber;
	}

	public void setCourseReferenceNumber(int courseReferenceNumber) {
		this.courseReferenceNumber = courseReferenceNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
	
	
	
	
	

