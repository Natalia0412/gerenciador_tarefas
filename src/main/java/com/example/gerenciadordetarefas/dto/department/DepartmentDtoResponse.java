package com.example.gerenciadordetarefas.dto.department;

import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class DepartmentDtoResponse {
    private String id;
    private String name;
    private String description;
    private List<UserInfoDto> users;
}
