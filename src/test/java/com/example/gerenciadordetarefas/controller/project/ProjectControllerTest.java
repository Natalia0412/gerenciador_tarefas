package com.example.gerenciadordetarefas.controller.project;

import com.example.gerenciadordetarefas.dto.department.DepartmentDtoResponse;
import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.dto.project.ProjectDtoResponse;
import com.example.gerenciadordetarefas.dto.task.TaskInfoDto;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.service.project.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
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

    private final User user = User.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .assignedTasks(new ArrayList<>())
            .build();
    private final UserInfoDto user2 = UserInfoDto.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .build();
    private final Task task1 = Task.builder()
            .id(TASK_ID_1)
            .title("teste")
            .description("teste aqui")
            .assigne(Collections.singletonList(user))
            .build();

    private final Task task2 = Task.builder()
            .id(TASK_ID_2)
            .title("teste 2")
            .description("teste aqui 2")
            .assigne(Collections.singletonList(user))
            .build();

    private final TaskInfoDto taskInfo = TaskInfoDto.builder()
            .idTask(TASK_ID_1)
            .title("teste")
            .assigne(Collections.singletonList(user2))
            .build();

    private final ProjectDtoResponse projectDtoResponse1 = ProjectDtoResponse.builder()
            .name("App limpar casa")
            .description("Este app vai ajudar a pessoas a se organizar para limpar a casa")
            .taskList(Collections.singletonList(taskInfo))
            .build();
    private final ProjectDtoResponse projectDtoResponse2 = ProjectDtoResponse.builder()
            .name("App ajuda estudos")
            .description("Este app vai ajudar a pessoas a se organizar nos estudos")
            .taskList(new ArrayList<>())
            .build();
    private final List<ProjectDtoResponse> projectDtoResponse () {
        List<ProjectDtoResponse> projectDtoResponseList = new ArrayList<>();
              projectDtoResponseList.add(projectDtoResponse1);
              projectDtoResponseList.add(projectDtoResponse2);
              return  projectDtoResponseList;
    }


    @BeforeEach
    void setUp() {
        Mockito.lenient().when(projectService.createProject(projectDto1)).thenReturn(projectDtoResponse1);
        Mockito.lenient().when(projectService.updateProject(projectDto1, PROJECT_ID_1)).thenReturn(projectDtoResponse1);
        Mockito.lenient().when(projectService.getProjectByIdResponse(PROJECT_ID_1)).thenReturn(projectDtoResponse1);
        Mockito.lenient().when(projectService.getAllProject()).thenReturn(projectDtoResponse());
    }

    @Test
    @DisplayName("Verify POST method")
    void addProject() {
        ResponseEntity<ProjectDtoResponse> responseEntity = projectController.addProject(projectDto1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(projectDtoResponse1, responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify PUT method")
    void alterProject() {
        ResponseEntity<ProjectDtoResponse> responseEntity = projectController.alterProject(projectDto1, PROJECT_ID_1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(projectDtoResponse1, responseEntity.getBody());
    }


    @Test
    @DisplayName("Verify GET method by Id")
    void getById() {
        ResponseEntity<ProjectDtoResponse> responseEntity = projectController.getById(PROJECT_ID_1);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(projectDtoResponse1, responseEntity.getBody());
        for (int i=0; i<projectDtoResponse1.getTaskList().size(); i++){
            TaskInfoDto  expect =projectDtoResponse1.getTaskList().get(i);
            TaskInfoDto actual = responseEntity.getBody().getTaskList().get(i);
            Assertions.assertEquals(expect.getIdTask(), actual.getIdTask());
            Assertions.assertEquals(expect.getTitle(), actual.getTitle());
            Assertions.assertEquals(expect.getAssigne(), actual.getAssigne());
        }
    }

    @Test
    @DisplayName("Verify GET method")
    void returnAllProjects() {
        ResponseEntity<ProjectDtoResponse> responseEntity = projectController.getById(PROJECT_ID_1);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(projectDtoResponse1, responseEntity.getBody());
        for (int i=0; i<projectDtoResponse1.getTaskList().size(); i++){
            TaskInfoDto  expect =projectDtoResponse1.getTaskList().get(i);
            TaskInfoDto actual = responseEntity.getBody().getTaskList().get(i);
            Assertions.assertEquals(expect.getIdTask(), actual.getIdTask());
            Assertions.assertEquals(expect.getTitle(), actual.getTitle());
            Assertions.assertEquals(expect.getAssigne(), actual.getAssigne());
        }
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Verify DELETE method")
    void deleteProjectById() {
        ResponseEntity<?> responseEntity = projectController.deleteProjectById(PROJECT_ID_1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}