package com.example.gerenciadordetarefas.util.mapper.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.dto.task.TaskDtoResponse;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
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

    public static TaskDtoResponse taskToTaskDtoResponse(Task task) {
        return TaskDtoResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .dateOfCreation(task.getDateOfCreation())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .assigne(task.getAssigne().stream()
                        .map(user -> UserInfoDto.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .build()
                        ).toList()
                )
                .build();
    }
    public static List<TaskDtoResponse> taskToTaskDtoResponse(List<Task> tasks) {
        return tasks.stream()
                .map(task -> TaskDtoResponse.builder()
                        .title(task.getTitle())
                        .dateOfCreation(task.getDateOfCreation())
                        .dueDate(task.getDueDate())
                        .priority(task.getPriority())
                        .assigne(task.getAssigne().stream()
                                .map(user -> UserInfoDto.builder()
                                        .name(user.getName())
                                        .build()
                                ).toList()
                        )
                        .build()
                )
                .toList();
    }

//    public static TaskDtoResponse taskToTaskDtoResponse(Task task) {
//        return TaskDtoResponse.builder()
//                .id(task.getId())
//                .taskDto(TaskMapper.taskToTaskDTO(task))
//                .build();
//    }

//    public static TaskDto taskToTaskDTO(Task task){
//      return  TaskDto.builder()
//                .title(task.getTitle())
//                .description(task.getDescription())
//                .dateOfCreation(task.getDateOfCreation())
//                .dueDate(task.getDueDate())
//                .priority(task.getPriority())
//                .assigne(task.getAssigne().stream()
//                        .map(user -> UserInfoDto.builder()
//                                .id(user.getId())
//                                .name(user.getName())
//                                .build()
//                        ).collect(Collectors.toList())
//                )
//                .build();
//    }





}
