package com.example.gerenciadordetarefas.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private String name;
    private String description;
    private List<String> assignedTasks;
}
