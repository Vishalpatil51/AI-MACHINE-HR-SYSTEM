package com.employee.assignments.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.assignments.controller.EmployeeController;
import com.employee.assignments.dto.EmployeeReq;
import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.model.Employee;
import com.employee.assignments.service.EmployeeService;

class EmployeeControllerTest {

    @Mock
    private EmployeeService empSer;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;
    private EmployeeReq employeeReq;
    private UUID employeeId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeId = UUID.randomUUID();

        employee = new Employee();

        employeeReq = new EmployeeReq();
    }

    @Test
    void testCreateEmployee() {

        when(empSer.saveEmployee(employeeReq)).thenReturn(employee);

        ResponseEntity<Employee> response =
                employeeController.createEmployee(employeeReq);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());

        verify(empSer).saveEmployee(employeeReq);
    }

    @Test
    void testGetAllEmployee() {

        List<Employee> employeeList = Arrays.asList(
                new Employee(),
                new Employee()
        );

        when(empSer.getAllEmp()).thenReturn(employeeList);

        ResponseEntity<List<Employee>> response =
                employeeController.getAllEmployee();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(employeeList, response.getBody());

        verify(empSer).getAllEmp();
    }

    @Test
    void testGetById() throws EmployeeNotFoundException {

        when(empSer.getById(employeeId)).thenReturn(employee);

        ResponseEntity<Employee> response =
                employeeController.getById(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());

        verify(empSer).getById(employeeId);
    }

    @Test
    void testGetByIdThrowsException() throws EmployeeNotFoundException {

        when(empSer.getById(employeeId))
                .thenThrow(new EmployeeNotFoundException("Employee not found"));

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeController.getById(employeeId);
        });

        verify(empSer).getById(employeeId);
    }
}