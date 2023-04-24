package edu.unca.csci338.controllers;

import java.util.ArrayList;
import edu.unca.csci338.domain.model.CourseInstance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unca.csci338.domain.data.CourseInstanceData;

@RestController
public class CourseInstanceController {
	
	private CourseInstanceData cd = new CourseInstanceData();
	
	public CourseInstanceController(){
		cd.Connect();
	}
	
	@GetMapping("/courseinstances/")
	public ResponseEntity<ArrayList<CourseInstance>> CourseInstances() {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(cd.getCourseInstances());
	}

	@GetMapping("/courseinstance/{id}")
	public ResponseEntity<CourseInstance> CourseInstanceId(@PathVariable int id) {
		
		return ResponseEntity.ok(cd.getCourseInstance(id));
	}
	
	@GetMapping("/courseinstance/")
	public ResponseEntity<CourseInstance> CourseInstance() {
		return ResponseEntity.ok(cd.getMostRecent());
	}
	
	@PostMapping("/courseinstance/")
	public ResponseEntity CourseInstancePost(@RequestBody(required = true) CourseInstance CourseInstance) {
		cd.updateCourseInstance(CourseInstance);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping("/courseinstance/")
	public ResponseEntity putCourseInstance(@RequestBody(required = true) CourseInstance CourseInstance) {
		cd.insertCourseInstance(CourseInstance);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/courseinstance/{id}")
	public ResponseEntity deleteCourseInstance(@PathVariable int id) {
		CourseInstance exists = cd.getCourseInstance(id);
		if(exists==null) {
			return ResponseEntity.notFound().build();
		}
		cd.deleteCourseInstance(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
