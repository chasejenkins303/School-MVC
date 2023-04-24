package edu.unca.csci338.domain.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.unca.csci338.domain.model.Student;

public class Data {
	
	protected Connection conn = null;
	public boolean connected=false;
	
	public void Connect() {
        
        try 
        	{
        		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + "edu_unca_csci338", "root", "Panthers#1"); //
        			
	            if (conn != null) {
	                System.out.println("Connected to the database!");
	                connected = true;
	            } else {
	                System.out.println("Failed to make connection!");
	            }
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	
	        }
	}
	
	public ResultSet getAll(String table) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
				
		try {
			preparedStatement = conn.prepareStatement("Select * from "+table);
			 resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return resultSet;
		
	}
	
	public ResultSet getById(String table, int ID) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conn.prepareStatement("Select * from " +table+ " Where id = " + String.valueOf(ID));
			 resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultSet;
	}
	
	public ResultSet getRecent(String table) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conn.prepareStatement("Select * from "+table+" Order by id DESC Limit 1");
			 resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultSet;
		
	}
	
	public void delete(int ID, String table) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM " +table+ " WHERE id = " + String.valueOf(ID));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public void Disconnect() {
		try {
			conn.close();
			connected=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getConnectStatus() {
		return connected;
	}

}
