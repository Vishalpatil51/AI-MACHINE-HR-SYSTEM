package com.employee.assignments.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.employee.assignments.model.Employee;
import com.employee.assignments.model.Project;

class ProjectTest {

    @Test
    void testGetterAndSetter() {

        UUID id = UUID.randomUUID();

        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());

        Project project = new Project();

        project.setId(id);
        project.setName("Employee Management");
        project.setRepo("https://github.com/project/repo");
        project.setEmployees(employees);

        assertEquals(id, project.getId());
        assertEquals("Employee Management", project.getName());
        assertEquals("https://github.com/project/repo", project.getRepo());
        assertEquals(employees, project.getEmployees());
    }

    @Test
    void testAllArgsConstructor() {

        UUID id = UUID.randomUUID();

        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());

        Project project = new Project(
                id,
                "Employee Management",
                "https://github.com/project/repo",
                employees
        );

        assertEquals(id, project.getId());
        assertEquals("Employee Management", project.getName());
        assertEquals("https://github.com/project/repo", project.getRepo());
        assertEquals(employees, project.getEmployees());
    }

    @Test
    void testBuilder() {

        UUID id = UUID.randomUUID();

        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());

        Project project = Project.builder()
                .id(id)
                .name("Assignment System")
                .repo("https://github.com/repo")
                .employees(employees)
                .build();

        assertEquals(id, project.getId());
        assertEquals("Assignment System", project.getName());
        assertEquals("https://github.com/repo", project.getRepo());
        assertEquals(employees, project.getEmployees());
    }

    @Test
    void testNoArgsConstructor() {

        Project project = new Project();

        assertNotNull(project);
    }
}