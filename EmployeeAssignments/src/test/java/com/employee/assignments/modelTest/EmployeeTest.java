package com.employee.assignments.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.employee.assignments.enums.Role;
import com.employee.assignments.model.Employee;
import com.employee.assignments.model.Project;

class EmployeeTest {

    @Test
    void testGetterAndSetter() {

        UUID id = UUID.randomUUID();

        LocalDate birthDate = LocalDate.of(1995, 5, 20);
        LocalDate recruitmentDate = LocalDate.of(2024, 1, 10);

        Set<Project> projects = new HashSet<>();
        projects.add(new Project());

        Employee employee = new Employee();

        employee.setId(id);
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setBirthDate(birthDate);
        employee.setGender("Male");
        employee.setRole(Role.DEVELOPER);
        employee.setPosition("Java Developer");
        employee.setRecruitmentDate(recruitmentDate);
        employee.setScore(90);
        employee.setSalary(80000.0);
        employee.setTeam("Backend");
        employee.setProjects(projects);

        assertEquals(id, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals(birthDate, employee.getBirthDate());
        assertEquals("Male", employee.getGender());
        assertEquals(Role.DEVELOPER, employee.getRole());
        assertEquals("Java Developer", employee.getPosition());
        assertEquals(recruitmentDate, employee.getRecruitmentDate());
        assertEquals(90, employee.getScore());
        assertEquals(80000.0, employee.getSalary());
        assertEquals("Backend", employee.getTeam());
        assertEquals(projects, employee.getProjects());
    }

    @Test
    void testAllArgsConstructor() {

        UUID id = UUID.randomUUID();

        LocalDate birthDate = LocalDate.of(1995, 5, 20);
        LocalDate recruitmentDate = LocalDate.of(2024, 1, 10);

        Set<Project> projects = new HashSet<>();
        projects.add(new Project());

        Employee employee = new Employee(
                id,
                "John Doe",
                "john@example.com",
                birthDate,
                "Male",
                Role.DEVELOPER,
                "Java Developer",
                recruitmentDate,
                90,
                80000.0,
                "Backend",
                projects
        );

        assertEquals(id, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals(birthDate, employee.getBirthDate());
        assertEquals("Male", employee.getGender());
        assertEquals(Role.DEVELOPER, employee.getRole());
        assertEquals("Java Developer", employee.getPosition());
        assertEquals(recruitmentDate, employee.getRecruitmentDate());
        assertEquals(90, employee.getScore());
        assertEquals(80000.0, employee.getSalary());
        assertEquals("Backend", employee.getTeam());
        assertEquals(projects, employee.getProjects());
    }

    @Test
    void testBuilder() {

        UUID id = UUID.randomUUID();

        Set<Project> projects = new HashSet<>();
        projects.add(new Project());

        Employee employee = Employee.builder()
                .id(id)
                .name("John Doe")
                .email("john@example.com")
                .role(Role.HR)
                .position("HR Manager")
                .score(85)
                .salary(60000.0)
                .team("HR")
                .projects(projects)
                .build();

        assertEquals(id, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals(Role.HR, employee.getRole());
        assertEquals("HR Manager", employee.getPosition());
        assertEquals(85, employee.getScore());
        assertEquals(60000.0, employee.getSalary());
        assertEquals("HR", employee.getTeam());
        assertEquals(projects, employee.getProjects());
    }

    @Test
    void testNoArgsConstructor() {

        Employee employee = new Employee();

        assertNotNull(employee);
    }
}