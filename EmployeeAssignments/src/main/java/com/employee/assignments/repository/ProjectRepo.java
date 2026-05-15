package com.employee.assignments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.assignments.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, UUID>{

}
