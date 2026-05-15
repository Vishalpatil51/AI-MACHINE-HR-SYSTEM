package com.employee.assignments.dtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.employee.assignments.dto.ProjectReq;

class ProjectReqTest {

    @Test
    void testProjectReqGetterSetter() {

        ProjectReq projectReq = new ProjectReq();

        projectReq.setName("Employee Management System");
        projectReq.setRepo("https://github.com/project/repo");

        assertEquals("Employee Management System", projectReq.getName());
        assertEquals("https://github.com/project/repo", projectReq.getRepo());
    }

    @Test
    void testNoArgsConstructor() {

        ProjectReq projectReq = new ProjectReq();

        assertNotNull(projectReq);
    }
}