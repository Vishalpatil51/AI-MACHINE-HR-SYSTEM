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

import com.employee.assignments.dto.EmployeeReq;
import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.model.Employee;
import com.employee.assignments.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService empSer;
	
	public EmployeeController(EmployeeService empSer) {
		this.empSer=empSer;
	}
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeReq e){
		return new ResponseEntity<Employee>(empSer.saveEmployee(e),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return new ResponseEntity<List<Employee>>(empSer.getAllEmp(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable UUID id) throws EmployeeNotFoundException{
		return new ResponseEntity<Employee>(empSer.getById(id),HttpStatus.OK);
	}
	

}
