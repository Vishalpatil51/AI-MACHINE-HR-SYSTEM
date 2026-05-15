package com.employee.assignments.exceptionsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.employee.assignments.exceptions.EmployeeNotFoundException;

class EmployeeNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {

        EmployeeNotFoundException exception =
                new EmployeeNotFoundException("Employee not found");

        assertEquals("Employee not found", exception.getMessage());
    }
}