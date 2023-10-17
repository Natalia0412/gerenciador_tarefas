package com.example.gerenciadordetarefas.model.task;

import com.example.gerenciadordetarefas.enums.task.FeatureType;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskFeature extends Task {
    private FeatureType featureType;
    private LocalDateTime  realTimeSpent;
    private LocalDateTime estimatedTime;

    public TaskFeature(String id, String title, String description, LocalDate dateOfCreation, LocalDate dueDate, String priority, User assigne, Department department) {
        super(id, title, description, dateOfCreation, dueDate, priority, assigne, department);
    }
}
