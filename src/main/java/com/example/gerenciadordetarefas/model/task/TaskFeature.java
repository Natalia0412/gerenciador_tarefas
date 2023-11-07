package com.example.gerenciadordetarefas.model.task;

import com.example.gerenciadordetarefas.enums.task.FeatureType;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class TaskFeature extends Task {
    private FeatureType featureType;

    TaskFeature(String id, String title, String description, LocalDate dateOfCreation, LocalDate dueDate, String priority, List<User> assigne) {
        super(id, title, description, dateOfCreation, dueDate, priority, assigne);
    }
    private LocalDate  realTimeSpent;
    private LocalDate estimatedTime;



}
