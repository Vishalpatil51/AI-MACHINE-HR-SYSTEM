package com.employee.assignments.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.assignments.exceptions.ProjectNotFoundException;
import com.employee.assignments.model.Project;
import com.employee.assignments.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private ProjectService projectSer;
	
	public ProjectController(ProjectService projectSer) {
		this.projectSer = projectSer;
	}
	
	@PostMapping("/saveProject")
	public ResponseEntity<Project> saveProjects(@RequestBody Project pr){
		return new ResponseEntity<Project>(projectSer.saveProject(pr),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable UUID id) throws ProjectNotFoundException{
		return new ResponseEntity<Project>(projectSer.getById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAllProjects")
	public ResponseEntity<List<Project>> getAllProject(){
		return new ResponseEntity<List<Project>>(projectSer.getAllProjects(),HttpStatus.ACCEPTED);
	}

}
