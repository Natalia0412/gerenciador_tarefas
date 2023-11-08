package com.example.gerenciadordetarefas.controller.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.dto.project.ProjectDtoResponse;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.service.project.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;
    @Mock
    private ProjectService projectService;
    private static final String PROJECT_ID_1 = "652d960af866034b686cca97";
    private static final String PROJECT_ID_2 = "999d960af866034b686cca11";
    private static final String TASK_ID_1 = "652f6ab6813d692cef4795ba";
    private static final String TASK_ID_2 = "112f6ab6813d692cef4795ba";
    private static final String USER_ID_1 = "642f6ab6813d692cef4795cc";
    private final ProjectDto projectDto1 = ProjectDto.builder()
            .name("App limpar casa")
            .description("Este app vai ajudar a pessoas a se organizar para limpar a casa")
            .assignedTasks(Arrays.asList(TASK_ID_1,TASK_ID_2))
            .build();

    private final Task task1 = Task.builder()
            .id(TASK_ID_1)
            .title("teste")
            .
            .assignedTasks(Arrays.asList(TASK_ID_1,TASK_ID_2))
            .build();
    private final ProjectDtoResponse projectDtoResponse1 = ProjectDtoResponse.builder()
            .name("App limpar casa")
            .description("Este app vai ajudar a pessoas a se organizar para limpar a casa")
            .assignedTasks(Arrays.asList(TASK_ID_1,TASK_ID_2))
            .build();

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(projectService.createProject(projectDto1)).thenReturn(resDto);
    }

    @Test
    void addProject() {
    }

    @Test
    void alterProject() {
    }

    @Test
    void getById() {
    }

    @Test
    void returnAllProjects() {
    }

    @Test
    void deleteProjectById() {
    }
}