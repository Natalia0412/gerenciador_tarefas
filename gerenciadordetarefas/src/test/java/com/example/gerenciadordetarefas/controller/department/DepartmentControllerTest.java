package com.example.gerenciadordetarefas.controller.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.dto.department.DepartmentDtoResponse;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.service.department.DepartmentService;
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
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    @InjectMocks
    private DepartmentController departmentController;
    @Mock
    private DepartmentService departmentService;
    private static final String USER_ID_1 = "652f6ab6813d692cef4795ba";
    private static final String USER_ID_2 = "112f6ab6813d692cef4795ba";
    private static final String DPTO_ID_1 = "652d960af866034b686cca97";
    private static final String DPTO_ID_2 = "999d960af866034b686cca11";
    private final UserInfoDto user1 = UserInfoDto.builder()
            .id(USER_ID_1)
            .name("Maria Doe")
            .build();


    private final UserInfoDto user2 = UserInfoDto.builder()
            .id(USER_ID_2)
            .name("José Doe")
            .build();

    private final DepartmentDtoResponse resDto = DepartmentDtoResponse.builder()
            .id(DPTO_ID_1)
            .name("Vendas")
            .description("Departamento de vendas")
            .users(Arrays.asList(user1, user2))
            .build();

    private final DepartmentDtoResponse resDto2 = DepartmentDtoResponse.builder()
            .id(DPTO_ID_2)
            .name("Vendas")
            .description("Departamento de vendas")
            .users(new ArrayList<>())
            .build();
    private final DepartmentDto reqDto = DepartmentDto.builder()
            .name("Vendas")
            .description("Departamento de vendas")
            .users(Arrays.asList(USER_ID_1,USER_ID_2))
            .build();

    private  List<DepartmentDtoResponse> dptoList() {
        List<DepartmentDtoResponse> dtoResponseList = new ArrayList<>();
        dtoResponseList.add(resDto);
        dtoResponseList.add(resDto2);
        return dtoResponseList;
    }



    @BeforeEach
    void setUp() {
        Mockito.lenient().when(departmentService.createDepartment(reqDto)).thenReturn(resDto);
        Mockito.lenient().when(departmentService.updateDepartment(reqDto, DPTO_ID_1)).thenReturn(resDto);
        Mockito.lenient().when(departmentService.getDepartmentByIdResponse(DPTO_ID_1)).thenReturn(resDto);
        Mockito.lenient().when(departmentService.getAllDepartment()).thenReturn(dptoList());
    }



    @Test
    @DisplayName("Verify POST method")
    void addDepartment() {
        ResponseEntity<DepartmentDtoResponse> responseEntity = departmentController.addDepartment(reqDto);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(resDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify PUT method")
    void updateDepartment() {
        ResponseEntity<DepartmentDtoResponse> responseEntity = departmentController.updateDepartment(reqDto, DPTO_ID_1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(resDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("Verify GET method by Id")
    void getDepartmentId() {
        ResponseEntity<DepartmentDtoResponse> responseEntity = departmentController.getDepartmentId(DPTO_ID_1);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(resDto, responseEntity.getBody());
        for (int i=0; i<resDto.getUsers().size(); i++){
            UserInfoDto expect =resDto.getUsers().get(i);
            UserInfoDto actual = responseEntity.getBody().getUsers().get(i);
            Assertions.assertEquals(expect.getId(), actual.getId());
            Assertions.assertEquals(expect.getName(), actual.getName());
        }
    }

    @Test
    @DisplayName("Verify GET method")
    void returnAllDepartment() {
        ResponseEntity<List<DepartmentDtoResponse>> responseEntity = departmentController.returnAllDepartment();
        List<DepartmentDtoResponse> response = responseEntity.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(dptoList().size(), response.size());
        for (int i=0; i< dptoList().size(); i++){
            DepartmentDtoResponse expect = dptoList().get(i);
            DepartmentDtoResponse actual = response.get(i);
            Assertions.assertEquals(expect.getId(), actual.getId());
            Assertions.assertEquals(expect.getName(), actual.getName());
        }
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Verify DELETE method")
    void deleteDepartmentById() {
        ResponseEntity<?> responseEntity = departmentController.deleteDepartmentById(DPTO_ID_1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}