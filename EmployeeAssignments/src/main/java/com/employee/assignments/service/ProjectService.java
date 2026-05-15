package com.employee.assignments.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.employee.assignments.dto.ProjectReq;
import com.employee.assignments.exceptions.ProjectNotFoundException;
import com.employee.assignments.model.Project;
import com.employee.assignments.repository.ProjectRepo;

@Service
public class ProjectService {
	
	private ProjectRepo projectRepo;
	
	public ProjectService(ProjectRepo projectRepo) {
		this.projectRepo=projectRepo;
	}
	
	public Project saveProject(Project pr) {
		Project project= Project.builder()
				.name(pr.getName())
				.repo(pr.getRepo())
				.build();
		return projectRepo.save(project);
	}
	
	public Project getById(UUID id) throws ProjectNotFoundException {
		return projectRepo.findById(id).orElseThrow(()-> new ProjectNotFoundException("project not found for " +id));
	}
	
	public List<Project> getAllProjects(){
		return projectRepo.findAll();
	}

}
