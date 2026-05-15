package com.employee.assignments.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.assignments.exceptions.ProjectNotFoundException;
import com.employee.assignments.model.Project;
import com.employee.assignments.repository.ProjectRepo;
import com.employee.assignments.service.ProjectService;

class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private UUID projectId;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        projectId = UUID.randomUUID();

        project = Project.builder()
                .id(projectId)
                .name("Employee Management")
                .repo("https://github.com/project/repo")
                .build();
    }

    @Test
    void testSaveProject() {

        when(projectRepo.save(any(Project.class)))
                .thenReturn(project);

        Project savedProject =
                projectService.saveProject(project);

        assertNotNull(savedProject);
        assertEquals("Employee Management", savedProject.getName());
        assertEquals("https://github.com/project/repo",
                savedProject.getRepo());

        verify(projectRepo).save(any(Project.class));
    }

    @Test
    void testGetById() throws ProjectNotFoundException {

        when(projectRepo.findById(projectId))
                .thenReturn(Optional.of(project));

        Project result = projectService.getById(projectId);

        assertNotNull(result);
        assertEquals(projectId, result.getId());

        verify(projectRepo).findById(projectId);
    }

    @Test
    void testGetByIdThrowsException() {

        when(projectRepo.findById(projectId))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> {
            projectService.getById(projectId);
        });

        verify(projectRepo).findById(projectId);
    }

    @Test
    void testGetAllProjects() {

        List<Project> projectList = Arrays.asList(
                new Project(),
                new Project()
        );

        when(projectRepo.findAll()).thenReturn(projectList);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());

        verify(projectRepo).findAll();
    }
}