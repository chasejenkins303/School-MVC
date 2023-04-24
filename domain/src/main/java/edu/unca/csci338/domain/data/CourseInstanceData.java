package edu.unca.csci338.domain.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.unca.csci338.domain.model.*;



public class CourseInstanceData extends Data{
	
	
	private Connection conn = null;
	private static List<ICourseInstanceDataChangedEvent> courseInstanceChangedEvents=new ArrayList<ICourseInstanceDataChangedEvent>();
	
	
	public CourseInstance getCourseInstance(int ID) {
		ResultSet resultSet = null;
		CourseInstance course = null;
		resultSet = getById("course_instances",ID);

            try {
				while (resultSet.next()) {
				    
				  int id = resultSet.getInt("id");
				  int courseId=resultSet.getInt("type_id");
				  int profId=resultSet.getInt("professor_id");
				  int roomId = resultSet.getInt("room_id");
				  int start=resultSet.getInt("start_time");
				  int end=resultSet.getInt("end_time");
				  course = new CourseInstance(id, courseId, profId, roomId, start, end);
				  

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return course;

	}
	
	
	public ArrayList<CourseInstance> getCourseInstances() {
		ArrayList<CourseInstance> courses = new ArrayList<CourseInstance>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		resultSet = getAll("course_instances");

            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					  int courseId=resultSet.getInt("type_id");
					  int profId=resultSet.getInt("professor_id");
					  int roomId = resultSet.getInt("room_id");
					  int start=resultSet.getInt("start_time");
					  int end=resultSet.getInt("end_time");
					  CourseInstance course = new CourseInstance(id, courseId, profId, roomId, start, end);
					  courses.add(course);
				    
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(student.getID());
            return courses;
	}
	
	
	
	public CourseInstance getMostRecent() {
		ResultSet resultSet = null;
		CourseInstance course = null;
		resultSet=getRecent("course_instances");

            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					  int courseId=resultSet.getInt("type_id");
					  int profId=resultSet.getInt("professor_id");
					  int roomId = resultSet.getInt("room_id");
					  int start=resultSet.getInt("start_time");
					  int end=resultSet.getInt("end_time");
					  course = new CourseInstance(id, courseId, profId, roomId, start, end);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
            return course;
	}
	
	
	
	public void updateCourseInstance(CourseInstance course) {
		PreparedStatement prep=null;
		
		try {
			prep = conn.prepareStatement("UPDATE course_instances SET type_id = ?, professor_id = ?, room_id = ?, start_time = ?, end_time = ? WHERE id = ?");
			prep.setInt(1, course.getCourseId());
			prep.setInt(2, course.getProfId());
			prep.setInt(3, course.getRoomId());
			prep.setInt(4, course.getStartTime());
			prep.setInt(5, course.getEndTime());
			prep.setInt(6, course.getID());
			prep.executeUpdate();
			
			for(ICourseInstanceDataChangedEvent listener : courseInstanceChangedEvents) {
				listener.onCourseInstanceDataChanged(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteCourseInstance(int ID) {
		delete(ID, "course_instances");
	}
	
	
	public void insertCourseInstance(CourseInstance course) {
		PreparedStatement prep=null;
		ResultSet res=null;
		
		try {
			prep=conn.prepareStatement("insert into course_instances(type_id, professor_id, room_id, start_time, end_time) VALUES( ?,?,?,?,? )");
			
			prep.setInt(1, course.getCourseId());
			prep.setInt(2, course.getProfId());
			prep.setInt(3, course.getRoomId());
			prep.setInt(4, course.getStartTime());
			prep.setInt(5, course.getEndTime());
			
			prep.executeUpdate();
			
			
			CourseInstance course2 = getMostRecent();
			course.setID(course2.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void AddOnCourseInstanceDataChangeEventListner(ICourseInstanceDataChangedEvent listener) {
		courseInstanceChangedEvents.add(listener);
	}
	
	}

		
		
	



