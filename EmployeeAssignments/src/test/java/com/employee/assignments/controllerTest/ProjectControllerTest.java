package com.employee.assignments.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.assignments.controller.ProjectController;
import com.employee.assignments.exceptions.ProjectNotFoundException;
import com.employee.assignments.model.Project;
import com.employee.assignments.service.ProjectService;

class ProjectControllerTest {

    @Mock
    private ProjectService projectSer;

    @InjectMocks
    private ProjectController projectController;

    private UUID projectId;
    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        projectId = UUID.randomUUID();

        project = new Project();
    }

    @Test
    void testSaveProjects() {

        when(projectSer.saveProject(project)).thenReturn(project);

        ResponseEntity<Project> response =
                projectController.saveProjects(project);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(project, response.getBody());

        verify(projectSer).saveProject(project);
    }

    @Test
    void testGetProjectById() throws ProjectNotFoundException {

        when(projectSer.getById(projectId)).thenReturn(project);

        ResponseEntity<Project> response =
                projectController.getProjectById(projectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());

        verify(projectSer).getById(projectId);
    }

    @Test
    void testGetProjectByIdThrowsException() throws ProjectNotFoundException {

        when(projectSer.getById(projectId))
                .thenThrow(new ProjectNotFoundException("Project not found"));

        assertThrows(ProjectNotFoundException.class, () -> {
            projectController.getProjectById(projectId);
        });

        verify(projectSer).getById(projectId);
    }

    @Test
    void testGetAllProject() {

        List<Project> projectList = Arrays.asList(
                new Project(),
                new Project()
        );

        when(projectSer.getAllProjects()).thenReturn(projectList);

        ResponseEntity<List<Project>> response =
                projectController.getAllProject();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(projectList, response.getBody());

        verify(projectSer).getAllProjects();
    }
}