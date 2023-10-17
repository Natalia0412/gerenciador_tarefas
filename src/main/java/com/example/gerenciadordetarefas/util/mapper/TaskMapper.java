package com.example.gerenciadordetarefas.util.mapper;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskMapper {
    public static Task taskDtoToTask(TaskDto dto, Department department, User user) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dateOfCreation(dto.getDateOfCreation())
                .dueDate(dto.getDueDate())
                .priority(dto.getPriority())
                .assigne(user)
                .department(department)
                .build();
    }


}
