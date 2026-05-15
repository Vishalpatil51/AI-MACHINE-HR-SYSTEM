package com.employee.assignments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectReq {
	
	@NotBlank
	private String name;
	
	private String repo;

}
