package com.example.gerenciadordetarefas.service.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.repository.project.ProjectRepository;
import com.example.gerenciadordetarefas.service.task.TaskService;
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

    public Project createProject(ProjectDto projectDto){

        Project project = ProjectMapper.projectDtoToProject(projectDto);

        List<String> assignedTasks = projectDto.getAssignedTasks();

        assignedTasks.forEach(id -> {
            Task task = taskService.getTaskById(id);
            project.getTaskList().add(task);
        });
        projectRepository.save(project);
        return project;

    }




}
