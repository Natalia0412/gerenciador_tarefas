package com.example.gerenciadordetarefas.service.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.task.TaskRepository;
import com.example.gerenciadordetarefas.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository repository;
    @InjectMocks
    private  TaskService service ;

    @Mock
    private UserService userService;
    private static final String TASK_ID_1 = "652d960af866034b686cca97";
    private static final String TASK_ID_2 = "252d960af866034b686cca92";
    private static final String USER_ID_1 = "642f6ab6813d692cef4795cc";

    private final User user1 = User.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .assignedTasks(new ArrayList<>())
            .build();
    private final Task task = Task.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(user1))
            .build();

    private final TaskDto taskD = TaskDto.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(USER_ID_1))
            .build();



    @BeforeEach
    void setUp() {


        Mockito.lenient().when(userService.getUserById(USER_ID_1)).thenReturn(user1);
        Mockito.lenient().when(repository.save(Mockito.any(Task.class))).thenReturn(task);
        Mockito.lenient().when(repository.findById(TASK_ID_1)).thenReturn(Optional.of(task));
    }

    @Test
    @DisplayName("Verificar se o objeto Task está sendo criado ")
    void createTask() {
        service.createTask(taskD);
        verify(repository, times(1)).save(task);

    }

    @Test
    void updateTask() {
    }

    @Test
    void addToAssigne() {
    }

    @Test
    void getTaskById() {
        service.getTaskById(TASK_ID_1);
        verify(repository, times(1)).findById(TASK_ID_1);
    }

    @Test
    void getTaskByIdResponse() {
    }

    @Test
    void getAllTasks() {
    }

    @Test
    void deleteTask() {
    }
}