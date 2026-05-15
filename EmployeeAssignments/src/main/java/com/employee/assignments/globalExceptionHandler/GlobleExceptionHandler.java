package com.employee.assignments.globalExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.exceptions.ProjectNotFoundException;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	 public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(
	            EmployeeNotFoundException ex
	    ) {

	        Map<String, Object> response = new HashMap<>();

	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("error", "Employee Not Found");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	
	@ExceptionHandler(ProjectNotFoundException.class)
	 public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(
			 ProjectNotFoundException ex
	    ) {

	        Map<String, Object> response = new HashMap<>();

	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("error", "Employee Not Found");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

}
