package edu.unca.csci338.domain.model;

import java.util.ArrayList;
import java.util.Date;

public class CourseInstance {

	private int id;
	private int courseId;
	private int profId;
	private int roomId;
	private int startTime;
	private int endTime;
	

	public int getID()
	{
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public CourseInstance() {
		
	}
	
	public CourseInstance(int id, int courseId, int profId, int roomId, int start, int end) {
		this.id = id;
		this.courseId= courseId;
		this.profId = profId;
		this.roomId=roomId;
		this.startTime=start;
		this.endTime=end;
		
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}




}

