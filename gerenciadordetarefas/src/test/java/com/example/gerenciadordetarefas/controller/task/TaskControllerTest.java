package com.example.gerenciadordetarefas.controller.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.dto.task.TaskDtoResponse;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.service.task.TaskService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @InjectMocks
    private TaskController controller;
    @Mock
    private TaskService service;
    private static final String USER_ID_1 = "642f6ab6813d692cef4795cc";
    private static final String USER_ID_2 = "652d960af866034b686cca97";
    private static final String TASK_ID_1 = "652f6ab6813d692cef4795ba";
    private static final String TASK_ID_2 = "112f6ab6813d692cef4795ba";

    private final User user1 = User.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .assignedTasks(new ArrayList<>())
            .build();

    private final UserInfoDto userI = UserInfoDto.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .build();
    private final UserInfoDto userI1 = UserInfoDto.builder()
            .name("Maria Silva")
            .build();
    private final UserInfoDto userI2 = UserInfoDto.builder()
            .name("Caio Doe")
            .build();

    private final TaskDto task1 = TaskDto.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(USER_ID_1))
            .build();
    private final Task task2 = Task.builder()
            .id(TASK_ID_1)
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(user1))
            .build();

    private final TaskDtoResponse taskR = TaskDtoResponse.builder()
            .id(TASK_ID_1)
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(userI))
            .build();

    private final TaskDtoResponse taskR1 = TaskDtoResponse.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Arrays.asList(userI1,userI2))
            .build();

    private List<TaskDtoResponse> taskListR(){
        List<TaskDtoResponse> tListR = new ArrayList<>();
        tListR.add(taskR);
        tListR.add(taskR1);
        return tListR;
    }
    @BeforeEach
    void setUp() {
        Mockito.lenient().when(service.createTask(task1)).thenReturn(taskR);
        Mockito.lenient().when(service.getTaskByIdResponse(TASK_ID_1)).thenReturn(taskR);
        Mockito.lenient().when(service.updateTask(task1,TASK_ID_1)).thenReturn(taskR);
        Mockito.lenient().when(service.getAllTasks()).thenReturn(taskListR());
    }

    @Test
    @DisplayName("Verify POST method")
    void addTask() {
        ResponseEntity<TaskDtoResponse> responseEntity = controller.addTask(task1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(taskR, responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify PUT method")
    void alterTask() {
        ResponseEntity<TaskDtoResponse> responseEntity = controller.alterTask(task1, TASK_ID_1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(taskR, responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify GET method by Id")
    void getIndividualTask() {
        ResponseEntity<TaskDtoResponse> responseEntity = controller.getIndividualTask(TASK_ID_1);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(taskR, responseEntity.getBody());
        for (int i=0; i<taskR.getAssigne().size(); i++){
            UserInfoDto expect =taskR.getAssigne().get(i);
            UserInfoDto actual = responseEntity.getBody().getAssigne().get(i);
            Assertions.assertEquals(expect.getId(), actual.getId());
            Assertions.assertEquals(expect.getName(), actual.getName());
        }
    }

    @Test
    @DisplayName("Verify GET method")
    void returnAllTask() {
        ResponseEntity<List<TaskDtoResponse>> responseEntity = controller.returnAllTask();
        List<TaskDtoResponse> response = responseEntity.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(taskListR().size(), response.size());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        for (int i=0; i<taskListR().size(); i++){
            TaskDtoResponse expect = taskListR().get(i);
            TaskDtoResponse actual = response.get(i);
            Assertions.assertEquals(expect.getTitle(), actual.getTitle());
            Assertions.assertEquals(expect.getDescription(), actual.getDescription());
            Assertions.assertEquals(expect.getDateOfCreation(), actual.getDateOfCreation());
            Assertions.assertEquals(expect.getDueDate(), actual.getDueDate());
            Assertions.assertEquals(expect.getPriority(), actual.getPriority());
            Assertions.assertEquals(expect.getAssigne(), actual.getAssigne());
        }
    }

    @Test
    @DisplayName("Verify DELETE method")
    void deleteTaskById() {
        ResponseEntity<TaskDtoResponse> responseEntity = controller.deleteTaskById(TASK_ID_1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}