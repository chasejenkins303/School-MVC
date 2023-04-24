package edu.unca.csci338.domain.data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import edu.unca.csci338.domain.model.IRoomDataChangedEvent;
import edu.unca.csci338.domain.model.IStudentDataChangedEvent;
import edu.unca.csci338.domain.model.Room;
import edu.unca.csci338.domain.model.Student;

public class RoomData extends Data {


    private Connection connection = null;
	private static List<IRoomDataChangedEvent> roomChangedEvents=new ArrayList<IRoomDataChangedEvent>();


    public ArrayList<Room> getRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		ResultSet resultSet = null;

		resultSet=getAll("rooms");
            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					int buildingID = resultSet.getInt("building_id");
					int roomNumber= resultSet.getInt("room_number");
					int capacity = resultSet.getInt("capacity");
					String roomType = resultSet.getString("room_type");
					Room room = new Room(id, buildingID, roomNumber, capacity, roomType);
					rooms.add(room);
				    
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(student.getID());
            return rooms;
	}
    
    
    public Room getRoom(int ID) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Room room = null;
		resultSet=getById("rooms",ID);

            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					int buildingID = resultSet.getInt("building_id");
					int roomNumber= resultSet.getInt("room_number");
					int capacity = resultSet.getInt("capacity");
					String roomType = resultSet.getString("room_type");
					room = new Room(id, buildingID, roomNumber, capacity, roomType);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(student.getID());
            return room;
	}
    
    public Room getMostRecent() {
		ResultSet resultSet = null;
		Room room = null;
		
		resultSet=getRecent("rooms");
            try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					int buildingID = resultSet.getInt("building_id");
					int roomNumber= resultSet.getInt("room_number");
					int capacity = resultSet.getInt("capacity");
					String roomType = resultSet.getString("room_type");
					room = new Room(id, buildingID, roomNumber, capacity, roomType);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(student.getID());
            return room;
	}
    
    public void updateRoom(Room room) {
		PreparedStatement prep=null;
		
		try {
			prep = connection.prepareStatement("UPDATE rooms SET building_id = ?, room_number = ?, capacity = ?, room_type = ? WHERE id = ?");
			prep.setInt(1, room.getBuildingId());
			prep.setInt(2, room.getRoomNumber());
			prep.setInt(3, room.getCapacity());
			prep.setString(4, room.getRoomType());
			prep.setInt(5, room.getId());
			prep.executeUpdate();
			
			for(IRoomDataChangedEvent listener : roomChangedEvents) {
				listener.onRoomDataChanged(room);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    public void insertRoom(Room room) {
		PreparedStatement prep=null;
		ResultSet res=null;
		int roomId;
		
		try {
			prep=connection.prepareStatement("insert into rooms(building_id, room_number, capacity, room_type) VALUES( ?,?,?,? )");
			prep.setInt(1, room.getBuildingId());
			prep.setInt(2, room.getRoomNumber());
			prep.setInt(3, room.getCapacity());
			prep.setString(4, room.getRoomType());
			prep.executeUpdate();
			
			
			Room room2 = getMostRecent();
			room.setId(room2.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    public void deleteRoom(int ID) {

    	delete(ID,"rooms");
	}
    
    
    public static void AddOnStudentDataChangeEventListner(IRoomDataChangedEvent listener) {
		roomChangedEvents.add(listener);
	}

}
