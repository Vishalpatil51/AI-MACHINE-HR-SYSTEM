package com.employee.assignments.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.assignments.dto.EmployeeReq;
import com.employee.assignments.enums.Role;
import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.model.Employee;
import com.employee.assignments.repository.EmployeeRepo;
import com.employee.assignments.service.EmployeeService;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepo emprepo;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeReq employeeReq;
    private Employee employee;
    private UUID employeeId;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        employeeId = UUID.randomUUID();

        employeeReq = new EmployeeReq();
        employeeReq.setName("John Doe");
        employeeReq.setEmail("john@example.com");
        employeeReq.setBirthDate(LocalDate.of(1995, 5, 20));
        employeeReq.setRole(Role.DEVELOPER);
        employeeReq.setPosition("Java Developer");
        employeeReq.setRecruitmentDate(LocalDate.of(2024, 1, 1));
        employeeReq.setGender("Male");
        employeeReq.setScore(90);
        employeeReq.setSalary(85000.0);
        employeeReq.setTeam("Backend");

        employee = Employee.builder()
                .id(employeeId)
                .name(employeeReq.getName())
                .email(employeeReq.getEmail())
                .birthDate(employeeReq.getBirthDate())
                .role(employeeReq.getRole())
                .position(employeeReq.getPosition())
                .recruitmentDate(employeeReq.getRecruitmentDate())
                .gender(employeeReq.getGender())
                .score(employeeReq.getScore())
                .salary(employeeReq.getSalary())
                .team(employeeReq.getTeam())
                .build();
    }

    @Test
    void testSaveEmployee() {

        when(emprepo.save(any(Employee.class)))
                .thenReturn(employee);

        Employee savedEmployee =
                employeeService.saveEmployee(employeeReq);

        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
        assertEquals("john@example.com", savedEmployee.getEmail());
        assertEquals(Role.DEVELOPER, savedEmployee.getRole());

        verify(emprepo).save(any(Employee.class));
    }

    @Test
    void testGetAllEmp() {

        List<Employee> employeeList = Arrays.asList(
                new Employee(),
                new Employee()
        );

        when(emprepo.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmp();

        assertEquals(2, result.size());

        verify(emprepo).findAll();
    }

    @Test
    void testGetById() throws EmployeeNotFoundException {

        when(emprepo.findById(employeeId))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());

        verify(emprepo).findById(employeeId);
    }

    @Test
    void testGetByIdThrowsException() {

        when(emprepo.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getById(employeeId);
        });

        verify(emprepo).findById(employeeId);
    }
}