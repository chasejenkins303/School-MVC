package edu.unca.csci338.controllers;

import java.util.ArrayList;
import edu.unca.csci338.domain.model.Building;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unca.csci338.domain.data.BuildingData;

@RestController
public class BuildingController {
	
private BuildingData bd = new BuildingData();
	
	public BuildingController(){
		bd.Connect();
	}
	
	@GetMapping("/buildings/")
	public ResponseEntity<ArrayList<Building>> Buildings() {
		return ResponseEntity.ok(bd.getBuildings());
	}

	@GetMapping("/building/{id}")
	public ResponseEntity<Building> BuildingId(@PathVariable int id) {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(bd.getBuilding(id));
	}
	
	@GetMapping("/building/")
	public ResponseEntity<Building> Building() {
		return ResponseEntity.ok(bd.getMostRecent());
	}
	
	@PostMapping("/building/")
	public ResponseEntity BuildingPost(@RequestBody(required = true) Building building) {
		bd.updateBuilding(building);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping("/building/")
	public ResponseEntity putBuilding(@RequestBody(required = true) Building Building) {
		bd.addBuilding(Building);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/building/{id}")
	public ResponseEntity deleteBuilding(@PathVariable int id) {
		Building exists = bd.getBuilding(id);
		if(exists==null) {
			return ResponseEntity.notFound().build();
		}
		bd.deleteBuilding(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
