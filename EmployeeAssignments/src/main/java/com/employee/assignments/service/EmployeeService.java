package com.employee.assignments.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.employee.assignments.enums.Role;

import com.employee.assignments.dto.EmployeeReq;
import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.model.Employee;
import com.employee.assignments.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	private EmployeeRepo emprepo;
	
	public EmployeeService(EmployeeRepo emprepo) {
		this.emprepo=emprepo;
	}
	
	public Employee saveEmployee(EmployeeReq empreq) {
		Employee emp = Employee.builder()
		.name(empreq.getName())
		.email(empreq.getEmail())
		.birthDate(empreq.getBirthDate())
		.role(empreq.getRole())
		.position(empreq.getPosition())
		.recruitmentDate(empreq.getRecruitmentDate())
		.gender(empreq.getGender())
		.score(empreq.getScore())
		.salary(empreq.getSalary())
		.team(empreq.getTeam())
		.build();
				
		return emprepo.save(emp);
		
	}
	
	public List<Employee> getAllEmp(){
		return emprepo.findAll();
	}
	
	public Employee getById(UUID id) throws EmployeeNotFoundException {
		return emprepo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("employee not found for this id"));
	}
	

}
