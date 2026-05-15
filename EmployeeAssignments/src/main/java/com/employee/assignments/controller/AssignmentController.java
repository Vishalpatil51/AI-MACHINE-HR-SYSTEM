package com.employee.assignments.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.assignments.model.Assignment;
import com.employee.assignments.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
	
	private AssignmentService assignSer;
	
	public AssignmentController(AssignmentService assignSer) {
		this.assignSer =assignSer;
	}
	
	@PostMapping("/{employeeId}/{projectId}")
	public ResponseEntity<Assignment> assignM(@PathVariable("employeeId") UUID employeeId, @PathVariable("projectId") UUID projectId){
		return new ResponseEntity<Assignment>(assignSer.assign(employeeId, projectId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{employeeId}/{projectId}")
	public void unassugnM(@PathVariable UUID employeeId, @PathVariable UUID projectId) {
		assignSer.unassign(employeeId, projectId);
	}

}
