package edu.unca.csci338.domain.model;

public class Room {

    private int id;
    private int buildingId;
    private int roomNumber; 
    private int capacity; 
    private String roomType;
   

    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBuildingId() {
		return buildingId;
	}


	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public Room(int id, int buildingId, int roomNumber, int capacity, String roomType) { //, RoomType roomType) {
        this.id = id;
        this.buildingId=buildingId;
        this.roomNumber=roomNumber;
        this.capacity=capacity;
        this.roomType=roomType;
        
    }

   

	
}
