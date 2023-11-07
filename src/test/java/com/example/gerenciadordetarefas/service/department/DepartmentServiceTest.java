package com.example.gerenciadordetarefas.service.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.department.DepartmentRepository;
import com.example.gerenciadordetarefas.service.user.UserService;
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
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private UserService userService;

    private static final String DPTO_ID_1 = "652f347400c4c967d9aeb1e5";

    private static final String DPTO_ID_2 = "652f347400c4c967d9aeb1e0";
    private static final String USER_ID_1 = "652f6ab6813d692cef4795ba";
    private static final String USER_ID_2 = "6531d1bf1690450c0ff5960d";
    private static final String TASK_ID_1 = "652d960af866034b686cca97";

    private Department dptoTest1() {
        return Department.builder()
                .id(DPTO_ID_1)
                .name("Vendas")
                .description("Departamento teste de Vendas")
                .users(Collections.singletonList(userTest1()))
                .build();
    }

    private List<Department> dptoTest2() {
        List<Department> departments = new ArrayList<>();
        departments.add(Department.builder()
                .id(DPTO_ID_1)
                .name("Vendas")
                .description("Departamento teste de Vendas")
                .users(Arrays.asList(userTest1(),userTest2()))
                .build());
        departments.add(Department.builder()
                .id(DPTO_ID_2)
                .name("Compras")
                .description("Departamento teste de compras")
                .users(Arrays.asList(userTest1(),userTest2()))
                .build());
        return departments;
    }
    private Department dptoTest3() {
        return Department.builder()
                .name("Vendas")
                .description("Departamento teste de Vendas")
                .users(new ArrayList<>())
                .build();
    }

    private DepartmentDto dptoDtoTest1(){
        return DepartmentDto.builder()
                .name("Vendas")
                .description("Departamento teste de Vendas")
                .users(new ArrayList<>())
                .build();
    }

    private DepartmentDto dptoDtoTest2(){
        return DepartmentDto.builder()
                .name("Compras")
                .description("Department Compras")
                .users(Collections.singletonList(USER_ID_2))
                .build();
    }



    private User userTest1(){
        return  User.builder()
                .id(USER_ID_1)
                .name("Jose Doe")
                .assignedTasks(new ArrayList<>())
                .build();
    }


    private User userTest2(){
        return  User.builder()
                .id(USER_ID_2)
                .name("Maria Doe")
                .assignedTasks(Collections.singletonList(
                        Task.builder()
                                .id(TASK_ID_1)
                                .title("Teste")
                                .description("tarefas a fazer 3")
                                .dateOfCreation(LocalDate.now())
                                .dueDate(LocalDate.now().plusDays(1))
                                .assigne(new ArrayList<>())
                                .build()
                ) )
                .build();
    }


    @BeforeEach
    void setUp() {
        Mockito.lenient().when(userService.getUserById(USER_ID_1)).thenReturn(userTest1());
        Mockito.lenient().when(userService.getUserById(USER_ID_2)).thenReturn(userTest2());
        Mockito.lenient().when(departmentRepository.findById(DPTO_ID_1)).thenReturn(Optional.of(dptoTest1()));
        Mockito.lenient().when(departmentRepository.findAll()).thenReturn(dptoTest2());
    }

    @Test
    @DisplayName("Criar departmaneto sem lista de usuarios")
    void createDepartment() {
        departmentService.createDepartment(dptoDtoTest1());
        verify(departmentRepository, times(1)).save(dptoTest3());
    }

    @Test
    @DisplayName("Criar departamento com lista de usuarios")
    void createDepartmentWithList() {
        Department department = Department.builder()
                .name("Compras")
                .description("Department Compras")
                .users(Collections.singletonList(userTest2()))
                .build();
        departmentService.createDepartment(dptoDtoTest2());
        verify(departmentRepository, times(1)).save(department);
    }
    @Test
    @DisplayName("Criar departmaneto sem lista de usuarios")
    void updateDepartment() {
        DepartmentDto dto = DepartmentDto.builder()
                .name("Vendas")
                .description("Departamento teste de Vendas")
                .users(Collections.singletonList(USER_ID_1))
                .build();
        departmentService.updateDepartment(dto,DPTO_ID_1);
        verify(departmentRepository, times(1)).save(dptoTest1());
    }

    @Test
    @DisplayName("Adicionar lista de usuarios")
    void addToUsers() {
        var dto = DepartmentDto.builder()
                .name("teste")
                .description("department test")
                .users(Arrays.asList(USER_ID_1,USER_ID_2))
                .build();
        Department department = Department.builder()
                .name("teste")
                .description("department test")
                .users(new ArrayList<>())
                .build();
        departmentService.addToUsers(dto, department);
        verify(userService, times(2)).getUserById(Mockito.anyString());
        assertEquals(2, department.getUsers().size());
        assertTrue(department.getUsers().contains(userTest1()));
        assertTrue(department.getUsers().contains(userTest2()));
    }

    @Test
    @DisplayName("Trazer Departamento através do ID")
    void getDepartmentById() {
        departmentService.getDepartmentById(DPTO_ID_1);
        verify(departmentRepository, times(1)).findById(Mockito.anyString());
    }

    @Test
    @DisplayName("Trazer todos os departamentos")
    void getAllDepartment() {
        departmentService.getAllDepartment();
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deletar departament por id")
    void deleteDepartment() {
        departmentService.deleteDepartment(DPTO_ID_1);
        verify(departmentRepository, times(1)).deleteById(DPTO_ID_1);
    }

    @Test
    @DisplayName("Lançar erro de department não encontrado ")
    void testExpectedException() {

        String id = "1";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            departmentService.getDepartmentById(id);
        });

        Assertions.assertEquals("Department not found with id: "+ id ,
                thrown.getMessage());


    }
}