package com.example.gerenciadordetarefas.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserDto {
    private String name;
    private List<String> assignedTasks;
}
