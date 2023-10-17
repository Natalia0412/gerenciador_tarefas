package com.example.gerenciadordetarefas.dto.department;

import com.example.gerenciadordetarefas.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private String name;
    private String description;
    private List<User> users;
}
