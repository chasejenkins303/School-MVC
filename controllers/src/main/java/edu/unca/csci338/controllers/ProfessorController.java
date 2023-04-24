package edu.unca.csci338.controllers;

import java.util.ArrayList;
import edu.unca.csci338.domain.model.Professor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unca.csci338.domain.data.ProfessorData;

@RestController
public class ProfessorController {
	
	private ProfessorData pd = new ProfessorData();
	
	public ProfessorController(){
		pd.Connect();
		
	}
	
	@GetMapping("/professors/")
	public ResponseEntity<ArrayList<Professor>> Professors() {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(pd.getProfessors());
	}

	@GetMapping("/professor/{id}")
	public ResponseEntity<Professor> ProfessorId(@PathVariable int id) {
		//TODO Reference CSCS 338 project and Student object
		return ResponseEntity.ok(pd.getProfessorByID(id));
	}
	
	@GetMapping("/professor/")
	public ResponseEntity<Professor> Professor() {
		return ResponseEntity.ok(pd.getMostRecent());
	}
	
	@PostMapping("/professor/")
	public ResponseEntity ProfessorPost(@RequestBody(required = true) Professor professor) {
		pd.updateProfessor(professor);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping("/professor/")
	public ResponseEntity putProfessor(@RequestBody(required = true) Professor professor) {
		pd.addProfessor(professor);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping("/professor/{id}")
	public ResponseEntity deleteProfessor(@PathVariable int id) {
		Professor exists = pd.getProfessorByID(id);
		if(exists==null) {
			return ResponseEntity.notFound().build();
		}
		pd.deleteProfessor(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}

