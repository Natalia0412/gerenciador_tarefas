package com.example.gerenciadordetarefas.dto.project;

import com.example.gerenciadordetarefas.dto.task.TaskInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDtoResponse {
    private String id;
    private String name;
    private String description;
    private List<TaskInfoDto> taskList;
}
