package com.employee.assignments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.assignments.model.Assignment;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, UUID>{

}
