package com.employee.assignments.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.employee.assignments.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeReq {
	
	  private UUID id;

	  @NotBlank
	  private String name;

	  @Email
	  @NotBlank
	  private String email;

	  private LocalDate birthDate;

	  private String gender;
	  
	  @NotNull
	  private Role role;

	  private String position;

	  private LocalDate recruitmentDate;

	  @Min(0)
	  @Max(100)
	  private Integer score;

	  private Double salary;

	  private String team;

	

}
