package com.example.gerenciadordetarefas.service.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.user.UserRepository;
import com.example.gerenciadordetarefas.service.task.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Mock
    private TaskService taskService;

    @BeforeEach void setUp(){
//        when(taskService.getTaskById(anyString()))
//                .thenAnswer(invocation -> {
//                    String taskId = invocation.getArgument(0);
//                    var user = User.builder()
//                            .id("652d960af866034b686cca97")
//                            .name("Jose Doe")
//                            .build();
//
//                    String dataFormatada = "21/10/2023";
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                    LocalDate data = LocalDate.parse(dataFormatada, formatter);
//                    // Simule o comportamento do método getTaskById com base no taskId
//                    // Retorne a tarefa desejada ou uma tarefa de mock, se necessário
//                    Task task = Task.builder()
//                            .id("65314c9f67c549652704a96e")
//                            .title("Teste")
//                            .description("tarefas a fazer 3")
//                            .dateOfCreation(data)
//                            .dueDate(LocalDate.parse("22/10/2023", formatter))
//                            .assigne(Arrays.asList(user))
//                            .build();
//                    return task;
//                });
    }

    @Test
    @DisplayName("Verificar se o objeto User está sendo criado ")
    void createUser() {
        var userDto = UserDto.builder()
                .name("Maria Doe")
                .assignedTasks(new ArrayList<>())
                .build();

        var user = User.builder()
                .name("Maria Doe")
                .assignedTasks(new ArrayList<>())
                .build();
        userService.createUser(userDto);
       verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Verificar se o objeto User está sendo criado com lista de assignedTasks")
    void createUserWithList() {
        var user1 = User.builder()
                .id("852d960af866034b686cca98")
                .name("Jose Doe")
                .build();
        var userDto = UserDto.builder()
                .name("Maria Doe")
                .assignedTasks(Arrays.asList("652d960af866034b686cca97"))
                .build();

        when(taskService.getTaskById("652d960af866034b686cca97")).thenReturn(
                Task.builder()
                        .id("652d960af866034b686cca97")
                        .title("Teste")
                        .description("tarefas a fazer 3")
                        .dateOfCreation(LocalDate.now())
                        .dueDate(LocalDate.now().plusDays(1))
                        .assigne(Collections.singletonList(user1))
                        .build()
        );



        var user = User.builder()
                .name("Maria Doe")
                .assignedTasks(Collections.singletonList(
                        Task.builder()
                                .id("652d960af866034b686cca97")
                                .title("Teste")
                                .description("tarefas a fazer 3")
                                .dateOfCreation(LocalDate.now())
                                .dueDate(LocalDate.now().plusDays(1))
                                .assigne(Collections.singletonList(user1))
                                .build()
                ))
                .build();
        userService.createUser(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getAllUser() {
        //when(userService.getAllUser()).thenReturn(List<User> user = new )
    }

    @Test
    void addToTaskList() {
    }

    @Test
    void deleteUserById() {
    }
}