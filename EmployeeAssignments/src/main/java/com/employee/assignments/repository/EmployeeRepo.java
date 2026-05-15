package com.employee.assignments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.assignments.model.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID>{

}
