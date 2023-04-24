package edu.unca.csci338.domain.model;

public interface ICourseDataChangedEvent {

	public void onCourseDataChanged(Course course);
}
