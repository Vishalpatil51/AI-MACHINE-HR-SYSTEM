package com.employee.assignments.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.employee.assignments.model.Assignment;
import com.employee.assignments.model.Employee;
import com.employee.assignments.model.Project;

class AssignmentTest {

    @Test
    void testGetterAndSetter() {

        UUID id = UUID.randomUUID();

        Employee employee = new Employee();
        Project project = new Project();

        Assignment assignment = new Assignment();

        assignment.setId(id);
        assignment.setEmployee(employee);
        assignment.setProject(project);

        assertEquals(id, assignment.getId());
        assertEquals(employee, assignment.getEmployee());
        assertEquals(project, assignment.getProject());
    }

    @Test
    void testAllArgsConstructor() {

        UUID id = UUID.randomUUID();

        Employee employee = new Employee();
        Project project = new Project();

        Assignment assignment =
                new Assignment(id, employee, project);

        assertEquals(id, assignment.getId());
        assertEquals(employee, assignment.getEmployee());
        assertEquals(project, assignment.getProject());
    }

    @Test
    void testNoArgsConstructor() {

        Assignment assignment = new Assignment();

        assertNotNull(assignment);
    }

    @Test
    void testBuilder() {

        UUID id = UUID.randomUUID();

        Employee employee = new Employee();
        Project project = new Project();

        Assignment assignment = Assignment.builder()
                .id(id)
                .employee(employee)
                .project(project)
                .build();

        assertEquals(id, assignment.getId());
        assertEquals(employee, assignment.getEmployee());
        assertEquals(project, assignment.getProject());
    }
}