package com.example.gerenciadordetarefas.dto.department;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Data
public class DepartmentDto {
    private String name;
    private String description;
    private List<String> users;
}
