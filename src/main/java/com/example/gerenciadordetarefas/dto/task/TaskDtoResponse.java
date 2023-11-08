package com.example.gerenciadordetarefas.dto.task;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TaskDtoResponse {
    private String id;
    private TaskDto taskDto;


}
