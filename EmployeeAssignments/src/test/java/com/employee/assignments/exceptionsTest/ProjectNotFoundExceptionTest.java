package com.employee.assignments.exceptionsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.employee.assignments.exceptions.ProjectNotFoundException;

class ProjectNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {

        ProjectNotFoundException exception =
                new ProjectNotFoundException("Project not found");

        assertEquals("Project not found", exception.getMessage());
    }
}