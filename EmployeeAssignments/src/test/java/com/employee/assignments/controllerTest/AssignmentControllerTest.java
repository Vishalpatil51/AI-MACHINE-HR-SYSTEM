package com.employee.assignments.controllerTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.assignments.controller.AssignmentController;
import com.employee.assignments.model.Assignment;
import com.employee.assignments.service.AssignmentService;

class AssignmentControllerTest {

    @Mock
    private AssignmentService assignSer;

    @InjectMocks
    private AssignmentController assignmentController;

    private UUID employeeId;
    private UUID projectId;
    private Assignment assignment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeId = UUID.randomUUID();
        projectId = UUID.randomUUID();

        assignment = new Assignment();
    }

    @Test
    void testAssignM() {

        when(assignSer.assign(employeeId, projectId)).thenReturn(assignment);

        ResponseEntity<Assignment> response =
                assignmentController.assignM(employeeId, projectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignment, response.getBody());

        verify(assignSer).assign(employeeId, projectId);
    }

    @Test
    void testUnassignM() {

        assertDoesNotThrow(() ->
                assignmentController.unassugnM(employeeId, projectId));

        verify(assignSer).unassign(employeeId, projectId);
    }
}