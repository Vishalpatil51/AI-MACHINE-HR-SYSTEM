package com.employee.assignments.globalExceptionHandlerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.assignments.exceptions.EmployeeNotFoundException;
import com.employee.assignments.exceptions.ProjectNotFoundException;
import com.employee.assignments.globalExceptionHandler.GlobleExceptionHandler;

class GlobleExceptionHandlerTest {

    private GlobleExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobleExceptionHandler();
    }

    @Test
    void testHandleEmployeeNotFound() {

        EmployeeNotFoundException exception =
                new EmployeeNotFoundException("Employee not found");

        ResponseEntity<Map<String, Object>> response =
                handler.handleEmployeeNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(404, body.get("status"));
        assertEquals("Employee Not Found", body.get("error"));
        assertEquals("Employee not found", body.get("message"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void testHandleProjectNotFound() {

        ProjectNotFoundException exception =
                new ProjectNotFoundException("Project not found");

        ResponseEntity<Map<String, Object>> response =
                handler.handleEmployeeNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(404, body.get("status"));
        assertEquals("Employee Not Found", body.get("error"));
        assertEquals("Project not found", body.get("message"));
        assertNotNull(body.get("timestamp"));
    }
}