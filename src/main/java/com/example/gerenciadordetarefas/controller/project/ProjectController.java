package com.example.gerenciadordetarefas.controller.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping
    public Project addProject(@RequestBody ProjectDto dto){

        return projectService.createProject(dto);
    }

    @PutMapping("/{id}")
    public Project alterProject(@RequestBody ProjectDto dto, @PathVariable String id){
        return projectService.updateProject(dto, id);
    }

}