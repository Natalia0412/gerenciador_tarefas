package com.example.gerenciadordetarefas.service.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.user.UserRepository;
import com.example.gerenciadordetarefas.service.task.TaskService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Mock
    private TaskService taskService;

    private static final String TASK_ID_1 = "652d960af866034b686cca97";
    private static final String TASK_ID_2 = "252d960af866034b686cca92";

    private static final String USER_ID_1 = "652f6ab6813d692cef4795ba";
    private static final String USER_ID_2 = "6531d1bf1690450c0ff5960d";
    private User criadorTask(){
        return  User.builder()
                .id("852d960af866034b686cca98")
                .name("Jose Doe")
                .assignedTasks(new ArrayList<>())
                .build();
    }

    private User userTest(){
        return  User.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(createTask1()))
                .build();
    }

    private User userTest2(){
        return  User.builder()
                .id(USER_ID_2)
                .name("Jose Doe")
                .assignedTasks(Arrays
                        .asList(createTask1(),createTask2()))
                .build();
    }

    private List<User> userListTest() {
        List<User> users = new ArrayList<User>();
        users.add(userTest());
        users.add(userTest2());
        return  users;
    }

    private UserDto userDtoTest(){
        return  UserDto.builder()
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(TASK_ID_1))
                .build();
    }
    private Task createTask1() {
        return Task.builder()
                .id(TASK_ID_1)
                .title("Teste")
                .description("tarefas a fazer 3")
                .dateOfCreation(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(1))
                .assigne(Collections.singletonList(criadorTask()))
                .build();
    }

    private Task createTask2() {
        return Task.builder()
                .id(TASK_ID_2)
                .title("Outra Tarefa")
                .description("Descrição da outra tarefa")
                .dateOfCreation(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(2))
                .assigne(Collections.singletonList(criadorTask()))
                .build();
    }

    @BeforeEach public  void setUp(){
        Mockito.lenient().when(taskService.getTaskById(TASK_ID_1)).thenReturn(createTask1());
        Mockito.lenient().when(taskService.getTaskById(TASK_ID_2)).thenReturn(createTask2());
        Mockito.lenient().when(userRepository.findById(USER_ID_1)).thenReturn(Optional.of(userTest()));
        Mockito.lenient().when(userRepository.findAll()).thenReturn(userListTest());
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
                .assignedTasks(Collections.singletonList(TASK_ID_1))
                .build();
        var user = User.builder()
                .name("Maria Doe")
                .assignedTasks(Collections.singletonList(createTask1()))
                .build();
        userService.createUser(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Verificar se o objeto User está sendo Atualizado")
    void updateUser() {

        userService.updateUser(userDtoTest(), USER_ID_1);
        verify(userRepository, times(1)).save(userTest());
    }

    @Test
    @DisplayName("Verificar se o objeto User com id= ? está sendo retornado")
    void getUserById() {
        userService.getUserById(USER_ID_1);

        User expectedUser = User.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(createTask1()))
                .build();
        verify(userRepository, times(1)).findById(USER_ID_1);
        assertEquals(expectedUser, userTest());
    }

    @Test
    @DisplayName("Verificar se o objeto User com id= ? está sendo retornado pois esse é o da controller")
    void getUserByIdResponse(){
        userService.getUserById(USER_ID_1);

        User expectedUser = User.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(createTask1()))
                .build();
        verify(userRepository, times(1)).findById(USER_ID_1);
        assertEquals(expectedUser, userTest());
    }

    @Test
    @DisplayName("Retornar uma lista de usuários")
    void getAllUser() {
        userService.getAllUser();
        verify(userRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Adicionar Task a  lista de assignedTasks")
    void addToTaskList() {

        var userDto = UserDto.builder()
                .name("Maria Doe")
                .assignedTasks(Arrays.asList(TASK_ID_1, TASK_ID_2))
                .build();

        var user = User.builder()
                .name("Maria Doe")
                .assignedTasks(new ArrayList<>())
                .build();
        userService.addToTaskList(userDto, user);
        verify(taskService, times(2)).getTaskById(Mockito.anyString());
        assertEquals(2, user.getAssignedTasks().size());
        assertTrue(user.getAssignedTasks().contains(createTask1()));
        assertTrue(user.getAssignedTasks().contains(createTask2()));
    }

    @Test
    @DisplayName("Deletar User por id")
    void deleteUserById() {
        userService.deleteUserById(USER_ID_1);
        verify(userRepository, times(1)).deleteById(USER_ID_1);
    }

    @Test
    @DisplayName("Lançar erro de usuario não encontrado ")
    void testExpectedException() {

        String id = "1";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(id);
        });

        Assertions.assertEquals("User not found, id: "+ id ,
                 thrown.getMessage());


    }
}