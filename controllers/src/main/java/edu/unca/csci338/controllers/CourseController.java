package edu.unca.csci338.controllers;

import java.util.ArrayList;
import edu.unca.csci338.domain.model.Course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unca.csci338.domain.data.CourseData;

@RestController
public class CourseController {
	
	private CourseData cd = new CourseData();
	
	public CourseController(){
		cd.Connect();
	}
	
	@GetMapping("/courses/")
	public ResponseEntity<ArrayList<Course>> Courses() {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(cd.getCourses());
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<Course> CourseId(@PathVariable int id) {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(cd.getCourse(id));
	}
	
	@GetMapping("/course/")
	public ResponseEntity<Course> Course() {
		return ResponseEntity.ok(cd.getMostRecent());
	}
	
	@PostMapping("/course/")
	public ResponseEntity CoursePost(@RequestBody(required = true) Course course) {
		cd.updateCourse(course);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping("/course/")
	public ResponseEntity putCourse(@RequestBody(required = true) Course Course) {
		cd.createCourse(Course);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity deleteCourse(@PathVariable int id) {
		Course exists = cd.getCourse(id);
		if(exists==null) {
			return ResponseEntity.notFound().build();
		}
		cd.deleteCourse(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
