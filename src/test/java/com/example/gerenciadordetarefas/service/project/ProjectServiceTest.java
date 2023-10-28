package com.example.gerenciadordetarefas.service.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.repository.project.ProjectRepository;
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

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;

    @InjectMocks
    private ProjectService service;

    @Mock
    private TaskService taskService;

    private static final String TASK_ID_1 = "652d960af866034b686cca97";
    private static final String TASK_ID_2 = "252d960af866034b686cca92";
    private static final String PROJECT_ID_1 = "952d960af866034b686cca99";

    private Project projectTest(){
        return Project.builder()
                .name("teste")
                .description("projeto de teste")
                .taskList(new ArrayList<>())
                .build();
    }

    private Task createTask1() {
        return Task.builder()
                .id(TASK_ID_1)
                .title("Teste")
                .description("tarefas a fazer 3")
                .dateOfCreation(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(1))
                .assigne(new ArrayList<>())
                .build();
    }

    private Task createTask2() {
        return Task.builder()
                .id(TASK_ID_2)
                .title("Teste")
                .description("tarefas a fazer 3")
                .dateOfCreation(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(1))
                .assigne(new ArrayList<>())
                .build();
    }
    private Project projectTestTaskList(){
        return Project.builder()
                .name("teste")
                .description("projeto de teste")
                .taskList(Collections.singletonList(
                        createTask1()
                ))
                .build();
    }

    private Project projectTestTaskList2(){
        return Project.builder()
                .id(PROJECT_ID_1)
                .name("teste")
                .description("projeto de teste")
                .taskList(Collections.singletonList(
                        createTask1()
                ))
                .build();
    }

    private List<Project> projectList() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                projectTest(),
                projectTestTaskList()
        ));
        return  projects;
    }

    private ProjectDto projectDtoTest(){
        return ProjectDto.builder()
                .name("teste")
                .description("projeto de teste")
                .assignedTasks(new ArrayList<>())
                .build();
    }

    private ProjectDto projectDtoTestTask(){
        return ProjectDto.builder()
                .name("teste")
                .description("projeto de teste")
                .assignedTasks(Collections.singletonList(TASK_ID_1))
                .build();
    }

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(taskService.getTaskById(TASK_ID_1)).thenReturn(createTask1());
        Mockito.lenient().when(taskService.getTaskById(TASK_ID_2)).thenReturn(createTask2());
        Mockito.lenient().when(repository.findById(PROJECT_ID_1)).thenReturn(Optional.of(projectTestTaskList2()));
        Mockito.lenient().when(repository.findAll()).thenReturn(projectList());
    }

    @Test
    @DisplayName("Verificar se o objeto Project está sendo criado ")
    void createProject() {
        service.createProject(projectDtoTest());
        verify(repository, times(1)).save(projectTest());
    }

    @Test
    @DisplayName("Verificar se o objeto Project está sendo criado com lista de Task ")
    void createProjectList() {
        service.createProject(projectDtoTestTask());
        verify(repository, times(1)).save(projectTestTaskList());
    }

    @Test
    @DisplayName("Verificar se o objeto Project está sendo atualizado ")
    void updateProject() {
        service.updateProject(projectDtoTestTask(), PROJECT_ID_1);
        verify(repository, times(1)).save(projectTestTaskList2());

    }

    @Test
    @DisplayName("Retornar todos os objetos Project")
    void getAllProject() {
        service.getAllProject();
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Retornar apenas um objeto Project")
    void getProjectById() {
        service.getProjectById(PROJECT_ID_1);
        verify(repository, times(1)).findById(PROJECT_ID_1);

    }

    @Test
    @DisplayName("Adicionar lista de Task a Project")
    void addToAssignedTask() {
        var pDto = ProjectDto.builder()
                .name("teste")
                .description("projeto de teste")
                .assignedTasks(Arrays.asList(TASK_ID_1, TASK_ID_2))
                .build();

        var p = Project.builder()
                .name("teste")
                .description("projeto de teste")
                .taskList(new ArrayList<>())
                .build();

        service.addToAssignedTask(pDto, p);
        verify(taskService, times(2)).getTaskById(Mockito.anyString());
        assertEquals(2, p.getTaskList().size());
        assertTrue(p.getTaskList().contains(createTask1()));
        assertTrue(p.getTaskList().contains(createTask2()));
    }

    @Test
    @DisplayName("Deletar Project")
    void deleteProject() {
        service.deleteProject(PROJECT_ID_1);
        verify(repository, times(1)).deleteById(PROJECT_ID_1);

    }

    @Test
    @DisplayName("Lançar erro de project não encontrado ")
    void testExpectedException() {

        String id = "1";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.getProjectById(id);
        });

        Assertions.assertEquals("Project not found with id: "+ id ,
                thrown.getMessage());


    }
}