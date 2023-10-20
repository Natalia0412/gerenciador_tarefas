package com.example.gerenciadordetarefas.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private String name;
    private List<String> assignedTasks;
}
