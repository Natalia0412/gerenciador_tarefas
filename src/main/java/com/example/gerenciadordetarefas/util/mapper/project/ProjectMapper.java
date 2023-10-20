package com.example.gerenciadordetarefas.util.mapper.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.model.project.Project;

import java.util.ArrayList;

public class ProjectMapper {


    public static Project projectDtoToProject(ProjectDto projectDto){
        return Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .taskList(new ArrayList<>())
                .build();
    }

}
