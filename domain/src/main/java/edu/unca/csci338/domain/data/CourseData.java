package edu.unca.csci338.domain.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.unca.csci338.domain.model.Course;
import edu.unca.csci338.domain.model.ICourseDataChangedEvent;
import edu.unca.csci338.domain.model.IStudentDataChangedEvent;
import edu.unca.csci338.domain.model.Course;

public class CourseData extends Data{
  private Connection conn = null;

	private static List<ICourseDataChangedEvent> courseChangedEvents=new ArrayList<ICourseDataChangedEvent>();


	
	
	
	public ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		resultSet = getAll("course_types");

            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
			        int number = resultSet.getInt("number");
			        String name = resultSet.getString("name");
			        String description = resultSet.getString("description");
			        int department_id = resultSet.getInt("department_id");

			        Course course = new Course(name, number, description, department_id, id);
			        courses.add(course);
				    
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(Course.getID());
            return courses;
	}
	

  public Course getCourse(int ID) {
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Course course = null;
	    try {

	    	resultSet = getById("course_types", ID);
	      while (resultSet.next()) {
	        int id = resultSet.getInt("id");
	        int number = resultSet.getInt("number");
	        String name = resultSet.getString("name");
	        String description = resultSet.getString("description");
	        int department_id = resultSet.getInt("department_id");

	        course = new Course(name, number, description, department_id, id);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return course;
	  }


  public Course getMostRecent() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Course course = null;
		
		resultSet=getRecent("course_types");
          try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
			        int number = resultSet.getInt("number");
			        String name = resultSet.getString("name");
			        String description = resultSet.getString("description");
			        int department_id = resultSet.getInt("department_id");

			        course = new Course(name, number, description, department_id, id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          //System.out.println(Course.getID());
          return course;
	}
  
  
  
  public void updateCourse(Course course) {
    PreparedStatement prep = null;
    try {
      prep = conn.prepareStatement("UPDATE course_types SET number = ?, name = ?, description = ?, department_id = ? WHERE id = ?");
      prep.setInt(1, course.getCourseReferenceNumber());
      prep.setString(2, course.getNameOfCourse());
      prep.setString(3, course.getDescription());
      prep.setInt(4, course.getDepartment());
      prep.setInt(5, course.getId());
      prep.executeUpdate();
      
      for(ICourseDataChangedEvent listener : courseChangedEvents) {
			listener.onCourseDataChanged(course);
		}
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createCourse(Course course) {
    PreparedStatement prep = null;
    int courseID;
    try {
      prep = conn.prepareStatement("INSERT INTO course_types (number, name, description, department_id) VALUES (?,?,?,?)");
      prep.setInt(1, course.getCourseReferenceNumber());
      prep.setString(2, course.getNameOfCourse());
      prep.setString(3, course.getDescription());
      prep.setInt(4, course.getDepartment());
      
      courseID = prep.executeUpdate();
		
		course.setId(courseID);
      
    } catch (SQLException e) {
    e.printStackTrace();
    }
   }

    public void deleteCourse(int ID) {
    	delete(ID, "course_types");
    }

    public void closeConnection() {
    try {
    conn.close();
    } catch (SQLException e) {
    e.printStackTrace();
    }
    }
    
    public static void AddOnCourseDataChangeEventListner(ICourseDataChangedEvent listener) {
		courseChangedEvents.add(listener);
	}
    
    
}

