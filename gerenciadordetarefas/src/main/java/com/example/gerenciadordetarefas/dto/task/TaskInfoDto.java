package com.example.gerenciadordetarefas.dto.task;

import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class TaskInfoDto {
    private String idTask;
    private String title;
    private List<UserInfoDto> assigne;
}
