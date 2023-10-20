package com.example.gerenciadordetarefas.util.mapper.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static Task taskDtoToTask(TaskDto dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dateOfCreation(dto.getDateOfCreation())
                .dueDate(dto.getDueDate())
                .priority(dto.getPriority())
                .assigne(new ArrayList<>())
                .build();
    }





}
