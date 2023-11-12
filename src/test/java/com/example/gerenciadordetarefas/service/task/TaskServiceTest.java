package com.example.gerenciadordetarefas.service.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.dto.task.TaskDtoResponse;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.task.TaskRepository;
import com.example.gerenciadordetarefas.service.user.UserService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
class TaskServiceTest {
    @InjectMocks
    private  TaskService service ;

    @Mock
    private TaskRepository repository;


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

    private final UserInfoDto userIDTO = UserInfoDto.builder()
            .id(USER_ID_1)
            .name("Maria Silva")
            .build();
    private final Task task = Task.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(user1))
            .build();
    private final Task task1 = Task.builder()
            .id(TASK_ID_1)
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(new ArrayList<>(Arrays.asList(user1
            )))
            .build();
    private final List<Task> taskL = new ArrayList<>(Collections.singleton(task1));



    private final TaskDto taskD = TaskDto.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(USER_ID_1))
            .build();

    private final TaskDtoResponse taskR = TaskDtoResponse.builder()
            .title("teste")
            .description("teste aqui")
            .dateOfCreation(LocalDate.now())
            .dueDate(LocalDate.now())
            .assigne(Collections.singletonList(userIDTO))
            .build();




    @Test
    @DisplayName("Verificar se o objeto Task está sendo criado ")
    void createTask() {

        Mockito.when(userService.getUserById(USER_ID_1)).thenReturn(user1);
        service.createTask(taskD);
        verify(repository).save(task);

    }

    @Test
    @DisplayName("Verificar se o objeto Task está sendo atualizado ")
    void updateTask() {
        Mockito.when(repository.findById(TASK_ID_1)).thenReturn(Optional.of(task1));
        Mockito.when(userService.getUserById(USER_ID_1)).thenReturn(user1);

        service.updateTask(taskD, TASK_ID_1);
        verify(repository).save(task1);
    }

    @Test
    @DisplayName("Adicionar lista de User a Task")
    void addToAssigne() {
        Mockito.when(userService.getUserById(USER_ID_1)).thenReturn(user1);
        service.addToAssigne(taskD, task1);
        verify(userService, times(1)).getUserById(USER_ID_1);
        assertEquals(1, task.getAssigne().size());
        assertTrue(task.getAssigne().contains(user1));

    }

    @Test
    @DisplayName("Retornar apenas um objeto Task")
    void getTaskById() {
        Mockito.when(repository.findById(TASK_ID_1)).thenReturn(Optional.of(task));
        service.getTaskById(TASK_ID_1);
        verify(repository).findById(TASK_ID_1);
    }

    @Test
    void getTaskByIdResponse() {
        Mockito.when(repository.findById(TASK_ID_1)).thenReturn(Optional.of(task));
        service.getTaskById(TASK_ID_1);
        verify(repository).findById(TASK_ID_1);
    }

    @Test
    @DisplayName("Retornar todos os objetos Task")
    void getAllTasks() {
        Mockito.when(repository.findAll()).thenReturn(taskL);
        service.getAllTasks();
        verify(repository).findAll();

    }

    @Test
    @DisplayName("Deletar Task")
    void deleteTask() {
        Mockito.when(repository.findById(TASK_ID_1)).thenReturn(Optional.of(task));
        service.deleteTask(TASK_ID_1);
        verify(repository).deleteById(TASK_ID_1);
    }

    @Test
    @DisplayName("Lançar erro de task não encontrado ")
    void testExpectedException() {

        String id = "1";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.getTaskById(id);
        });

        Assertions.assertEquals("Task not found, id: "+ id ,
                thrown.getMessage());


    }
}