package com.example.gerenciadordetarefas.controller.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.dto.user.UserDtoResponse;
import com.example.gerenciadordetarefas.service.user.UserService;
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
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private static final String TASK_ID_1 = "652d960af866034b686cca97";
    private static final String USER_ID_1 = "652f6ab6813d692cef4795ba";

    private UserDto userDtoTest(){
        return  UserDto.builder()
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(TASK_ID_1))
                .build();
    }

    private UserDtoResponse userDtoResponseTest(){
        return UserDtoResponse.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList(TASK_ID_1))
                .build();
    }

    private UserDto userDtoTest1(){
        return UserDtoResponse.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(Collections.singletonList("Test"))
                .build();
    }
    private List<UserDto> userListTest() {
        List<UserDto> users = new ArrayList<UserDto>();
        users.add(userDtoTest1());
        return  users;
    }



    @BeforeEach
    void setUp() {
        Mockito.lenient().when(userService.createUser(userDtoTest())).thenReturn(userDtoResponseTest());
        Mockito.lenient().when(userService.updateUser(userDtoTest(), USER_ID_1)).thenReturn(userDtoResponseTest());
        Mockito.lenient().when(userService.getUserByIdResponse(USER_ID_1)).thenReturn(userDtoResponseTest());
        Mockito.lenient().when(userService.getAllUser()).thenReturn(userListTest());
    }

    @Test
    @DisplayName("Verify Post method")
    void addUser() {
        ResponseEntity<UserDtoResponse> responseEntity = userController.addUser(userDtoTest());
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(userDtoResponseTest(), responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify PUT method")
    void alterUser() {
        ResponseEntity<UserDtoResponse> responseEntity = userController.alterUser(userDtoTest(), USER_ID_1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(userDtoResponseTest(), responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify GET method by Id")
    void getByIdUser() {
        ResponseEntity<UserDtoResponse> responseEntity = userController.getByIdUser(USER_ID_1);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(userDtoResponseTest(), responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify GET method")
    void getUser() {
        ResponseEntity<List<UserDto>> responseEntity = userController.getUser();
        List<UserDto> responseUsers = responseEntity.getBody();
        Assertions.assertNotNull(responseUsers);
        Assertions.assertEquals(userListTest().size(), responseUsers.size());
        for (int i = 0; i < userListTest().size(); i++) {
            UserDto expectedUser = userListTest().get(i);
            UserDto actualUser = responseUsers.get(i);
            Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
            Assertions.assertEquals(expectedUser.getAssignedTasks(), actualUser.getAssignedTasks());
        }
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Verify Delete method")
    void deleteUser() {
        ResponseEntity<?> responseEntity = userController.deleteUser(USER_ID_1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}