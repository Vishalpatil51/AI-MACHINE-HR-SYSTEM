package com.employee.assignments.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.employee.assignments.model.Assignment;
import com.employee.assignments.model.Employee;
import com.employee.assignments.model.Project;
import com.employee.assignments.repository.AssignmentRepo;
import com.employee.assignments.repository.EmployeeRepo;
import com.employee.assignments.repository.ProjectRepo;

@Service
public class AssignmentService {
	
	private AssignmentRepo assignRepo;
	private EmployeeRepo empRepo;
	private ProjectRepo projectRepo;
	
	public AssignmentService(AssignmentRepo assignRepo, EmployeeRepo empRepo, ProjectRepo projectRepo) {
		this.assignRepo = assignRepo;
		this.empRepo = empRepo;
		this.projectRepo = projectRepo;
	}
	
	public Assignment assign(UUID employeeid, UUID projectId) {
		Employee employee= empRepo.findById(employeeid).orElseThrow();
		
		Project project = projectRepo.findById(projectId).orElseThrow();
		
		employee.getProjects().add(project);
		
		Assignment assignment = Assignment.builder()
				.employee(employee)
				.project(project)
				.build();
		
		empRepo.save(employee);
		return assignRepo.save(assignment);
	}
	
	public void unassign(UUID employeeId, UUID projectId) {
		
		Employee employee= empRepo.findById(employeeId).orElseThrow();
					employee.getProjects().removeIf(project -> project.getId().equals(projectId));
					empRepo.save(employee);
		
		
	}
	
	
	

}
