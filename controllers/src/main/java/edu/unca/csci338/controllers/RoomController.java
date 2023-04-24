package edu.unca.csci338.controllers;

import java.util.ArrayList;
import edu.unca.csci338.domain.model.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unca.csci338.domain.data.RoomData;

@RestController
public class RoomController {
	
private RoomData bd = new RoomData();
	
	public RoomController(){
		bd.Connect();
	}
	
	@GetMapping("/rooms/")
	public ResponseEntity<ArrayList<Room>> Rooms() {
		return ResponseEntity.ok(bd.getRooms());
	}

	@GetMapping("/room/{id}")
	public ResponseEntity<Room> RoomId(@PathVariable int id) {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(bd.getRoom(id));
	}
	
	@GetMapping("/room/")
	public ResponseEntity<Room> Room() {
		return ResponseEntity.ok(bd.getMostRecent());
	}
	
	@PostMapping("/room/")
	public ResponseEntity RoomPost(@RequestBody(required = true) Room Room) {
		bd.updateRoom(Room);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping("/room/")
	public ResponseEntity putRoom(@RequestBody(required = true) Room Room) {
		bd.insertRoom(Room);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/room/{id}")
	public ResponseEntity deleteRoom(@PathVariable int id) {
		Room exists = bd.getRoom(id);
		if(exists==null) {
			return ResponseEntity.notFound().build();
		}
		bd.deleteRoom(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}