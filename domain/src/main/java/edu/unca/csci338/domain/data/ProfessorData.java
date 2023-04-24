package edu.unca.csci338.domain.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.unca.csci338.domain.model.IProfessorDataChangedEvent;
import edu.unca.csci338.domain.model.IStudentDataChangedEvent;
import edu.unca.csci338.domain.model.Professor;
import edu.unca.csci338.domain.model.Student;

public class ProfessorData extends Data{
	
	private Connection conn = null;
	
	private static List<IProfessorDataChangedEvent> professorChangedEvents=new ArrayList<IProfessorDataChangedEvent>();
	
 		

	public ArrayList<Professor> getProfessors(){
//        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Professor professor = null;
        ArrayList<Professor> result = new ArrayList<Professor>();
        
        resultSet = super.getAll("professors");

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int department = resultSet.getInt("department_id");
                String status = resultSet.getString("status");
                boolean isHead = resultSet.getBoolean("is_head");
                professor = new Professor(id, firstName, lastName, department, status, isHead);
                   
                result.add(professor);
            }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return result;
    }
	
	
	public ArrayList<Professor> getProfessorsByDepartment(int department_ID){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Professor professor = null;
		ArrayList<Professor> result = new ArrayList<Professor>();
		
		try {
			preparedStatement = conn.prepareStatement("select * from professors where department_id =" + department_ID);
			 resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
        	while (resultSet.next()) {
			    int id = resultSet.getInt("id");
			    String firstName = resultSet.getString("first_name");
			    String lastName = resultSet.getString("last_name");
			    int department = resultSet.getInt("department_id"); // need to change to int?
			    professor = new Professor( firstName, lastName, department);
				   
			    result.add(professor);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return result;
	}
	
	
	public Professor getProfessorByID(int ID){
		//PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Professor professor = null;

		resultSet = getById("professors", ID);

        try {
        	while (resultSet.next()) {
        		int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int department = resultSet.getInt("department_id");
                String status = resultSet.getString("status");
                boolean isHead = resultSet.getBoolean("is_head");
                professor = new Professor(id, firstName, lastName, department, status, isHead);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return professor;
	}
	
	public Professor getMostRecent() {
		//PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Professor professor = null;
		
		resultSet = getRecent("professors");

            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");
	                int department = resultSet.getInt("department_id");
	                String status = resultSet.getString("status");
	                boolean isHead = resultSet.getBoolean("is_head");
	                professor = new Professor(id, firstName, lastName, department, status, isHead);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(student.getID());
            return professor;
	}
	
	
	
	public void addProfessor(Professor professor) {
		PreparedStatement prep=null;
		
		try {
			prep=conn.prepareStatement("insert into professors(first_name, last_name, department_id, status, is_head) VALUES( ?,?,?,?,? )");
			
			prep.setString(1, professor.getFirstName());
			prep.setString(2, professor.getLastName());
			prep.setInt(3, professor.getDepartment());
			prep.setString(4, professor.getStatus());
			prep.setBoolean(5, professor.getHead());
			prep.executeUpdate();
			
			
			Professor prof2 = getMostRecent();
			professor.setID(prof2.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void updateProfessor(Professor professor) {
		PreparedStatement prep=null;
		
		try {
			prep = conn.prepareStatement("UPDATE professors SET first_name = ?, last_name = ?, department_id = ?, status = ?, is_head = ? WHERE id = ?");
			prep.setString(1, professor.getFirstName());
			prep.setString(2, professor.getLastName());
			prep.setInt(3, professor.getDepartment());
			prep.setString(4, professor.getStatus());
			prep.setBoolean(5, professor.getHead());
			prep.setInt(6, professor.getID());
			prep.executeUpdate();
			
			for(IProfessorDataChangedEvent listener : professorChangedEvents) {
				listener.onProfessorDataChanged(professor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteProfessor(int ID) {
		
		delete(ID,"professors");
		
	}
	


	public static void AddOnProfessorDataChangeEventListner(IProfessorDataChangedEvent listener) {
		professorChangedEvents.add(listener);
		
	}

}

