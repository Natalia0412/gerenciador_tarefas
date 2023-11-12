package com.example.gerenciadordetarefas.dto.task;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class TaskDtoResponse {
    private String id;
    private String title;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private String priority;
    private List<UserInfoDto> assigne;


}
