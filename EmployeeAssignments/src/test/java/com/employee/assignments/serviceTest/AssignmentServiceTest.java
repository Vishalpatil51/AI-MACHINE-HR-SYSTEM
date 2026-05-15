package com.employee.assignments.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.assignments.model.Assignment;
import com.employee.assignments.model.Employee;
import com.employee.assignments.model.Project;
import com.employee.assignments.repository.AssignmentRepo;
import com.employee.assignments.repository.EmployeeRepo;
import com.employee.assignments.repository.ProjectRepo;
import com.employee.assignments.service.AssignmentService;

class AssignmentServiceTest {

    @Mock
    private AssignmentRepo assignRepo;

    @Mock
    private EmployeeRepo empRepo;

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private AssignmentService assignmentService;

    private UUID employeeId;
    private UUID projectId;

    private Employee employee;
    private Project project;
    private Assignment assignment;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        employeeId = UUID.randomUUID();
        projectId = UUID.randomUUID();

        employee = new Employee();
        employee.setId(employeeId);

        project = new Project();
        project.setId(projectId);

        assignment = Assignment.builder()
                .employee(employee)
                .project(project)
                .build();
    }

    @Test
    void testAssign() {

        when(empRepo.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(projectRepo.findById(projectId))
                .thenReturn(Optional.of(project));

        when(assignRepo.save(any(Assignment.class)))
                .thenReturn(assignment);

        Assignment result =
                assignmentService.assign(employeeId, projectId);

        assertNotNull(result);
        assertEquals(employee, result.getEmployee());
        assertEquals(project, result.getProject());

        verify(empRepo).findById(employeeId);
        verify(projectRepo).findById(projectId);
        verify(empRepo).save(employee);
        verify(assignRepo).save(any(Assignment.class));
    }

    @Test
    void testAssignEmployeeNotFound() {

        when(empRepo.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            assignmentService.assign(employeeId, projectId);
        });

        verify(empRepo).findById(employeeId);
    }

    @Test
    void testAssignProjectNotFound() {

        when(empRepo.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(projectRepo.findById(projectId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            assignmentService.assign(employeeId, projectId);
        });

        verify(empRepo).findById(employeeId);
        verify(projectRepo).findById(projectId);
    }

    @Test
    void testUnassign() {

        employee.getProjects().add(project);

        when(empRepo.findById(employeeId))
                .thenReturn(Optional.of(employee));

        assignmentService.unassign(employeeId, projectId);

        assertEquals(0, employee.getProjects().size());

        verify(empRepo).findById(employeeId);
        verify(empRepo).save(employee);
    }

    @Test
    void testUnassignEmployeeNotFound() {

        when(empRepo.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            assignmentService.unassign(employeeId, projectId);
        });

        verify(empRepo).findById(employeeId);
    }
}