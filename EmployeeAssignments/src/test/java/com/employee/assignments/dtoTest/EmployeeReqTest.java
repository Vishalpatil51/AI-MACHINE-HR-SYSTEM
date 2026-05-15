package com.employee.assignments.dtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.employee.assignments.dto.EmployeeReq;
import com.employee.assignments.enums.Role;

class EmployeeReqTest {

    @Test
    void testEmployeeReqGetterSetter() {

        EmployeeReq employeeReq = new EmployeeReq();

        UUID id = UUID.randomUUID();
        LocalDate birthDate = LocalDate.of(1998, 5, 10);
        LocalDate recruitmentDate = LocalDate.of(2024, 1, 15);

        employeeReq.setId(id);
        employeeReq.setName("John Doe");
        employeeReq.setEmail("john@example.com");
        employeeReq.setBirthDate(birthDate);
        employeeReq.setGender("Male");
        employeeReq.setRole(Role.ADMIN);
        employeeReq.setPosition("Software Engineer");
        employeeReq.setRecruitmentDate(recruitmentDate);
        employeeReq.setScore(95);
        employeeReq.setSalary(75000.0);
        employeeReq.setTeam("Development");

        assertEquals(id, employeeReq.getId());
        assertEquals("John Doe", employeeReq.getName());
        assertEquals("john@example.com", employeeReq.getEmail());
        assertEquals(birthDate, employeeReq.getBirthDate());
        assertEquals("Male", employeeReq.getGender());
        assertEquals(Role.ADMIN, employeeReq.getRole());
        assertEquals("Software Engineer", employeeReq.getPosition());
        assertEquals(recruitmentDate, employeeReq.getRecruitmentDate());
        assertEquals(95, employeeReq.getScore());
        assertEquals(75000.0, employeeReq.getSalary());
        assertEquals("Development", employeeReq.getTeam());
    }

    @Test
    void testEmployeeReqNoArgsConstructor() {

        EmployeeReq employeeReq = new EmployeeReq();

        assertNotNull(employeeReq);
    }
}