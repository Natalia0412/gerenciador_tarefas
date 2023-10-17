package com.example.gerenciadordetarefas.dto.user;

import com.example.gerenciadordetarefas.model.department.Department;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private String department;
}
