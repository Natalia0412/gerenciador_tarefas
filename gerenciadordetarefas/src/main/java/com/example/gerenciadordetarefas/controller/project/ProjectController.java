package com.example.gerenciadordetarefas.controller.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.dto.project.ProjectDtoResponse;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping
    public ResponseEntity<ProjectDtoResponse> addProject(@RequestBody ProjectDto dto){
        ProjectDtoResponse projectDtoResponse = projectService.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> alterProject(@RequestBody ProjectDto dto, @PathVariable String id){
        ProjectDtoResponse projectDtoResponse = projectService.updateProject(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> getById(@PathVariable String id){
        ProjectDtoResponse projectDtoResponse = projectService.getProjectByIdResponse(id);
        return ResponseEntity.ok(projectDtoResponse);
    }

    @GetMapping()
    public  ResponseEntity<List<ProjectDtoResponse>>returnAllProjects(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String id){
        this.projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
