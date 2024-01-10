package com.example.gerenciadordetarefas.service.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.dto.project.ProjectDtoResponse;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.repository.project.ProjectRepository;
import com.example.gerenciadordetarefas.service.task.TaskService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.project.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    public ProjectDtoResponse createProject(ProjectDto projectDto){

        Project project = ProjectMapper.projectDtoToProject(projectDto);
        if(projectDto.getAssignedTasks() != null){
            this.addToAssignedTask(projectDto, project);
        }
        projectRepository.save(project);
        return ProjectMapper.projectToProjectDtoResponse(project);

    }

    public ProjectDtoResponse updateProject(ProjectDto projectDto, String id){
        this.getProjectById(id);
        Project project = ProjectMapper.projectDtoToProject(projectDto);
        this.addToAssignedTask(projectDto, project);
        project.setId(id);
        projectRepository.save(project);
        return ProjectMapper.projectToProjectDtoResponse(project);

    }

    public List<ProjectDtoResponse> getAllProject(){
        return ProjectMapper.projectToProjectDtoResponse(projectRepository.findAll());

    }

    public ProjectDtoResponse getProjectByIdResponse(String id){
      Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found with id: " , id));
        return ProjectMapper.projectToProjectDtoResponse(project);
    }

    public Project getProjectById(String id){
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found with id: " , id));
        return project;
    }


    public Project addToAssignedTask(ProjectDto projectDto, Project project){
        List<String> assignedTasks = projectDto.getAssignedTasks();
        assignedTasks.forEach(id -> {
            Task task = taskService.getTaskById(id);
            project.getTaskList().add(task);
        });
        return project;
    }

    public void deleteProject(String id) {
        this.getProjectById(id);
        projectRepository.deleteById(id);
    }


}
