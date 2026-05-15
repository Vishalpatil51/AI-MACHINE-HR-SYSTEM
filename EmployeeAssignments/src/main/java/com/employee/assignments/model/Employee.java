package com.employee.assignments.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.employee.assignments.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "emplyees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	  private UUID id;

	  @Column(nullable = false)
	  private String name;

	  @Column(nullable = false, unique = true)
	  private String email;

	  private LocalDate birthDate;

	  private String gender;

	  @Enumerated(EnumType.STRING)
	  private Role role;

	  private String position;

	  private LocalDate recruitmentDate;

	  private Integer score;

	  private Double salary;

	  private String team;
	  
	  @ManyToMany
	  private Set<Project> projects=new HashSet<>();


}
