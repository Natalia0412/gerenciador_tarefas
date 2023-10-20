package com.example.gerenciadordetarefas.dto.department;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DepartmentDto {
    private String name;
    private String description;
    private List<String> users;
}
