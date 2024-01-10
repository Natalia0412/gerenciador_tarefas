package com.example.gerenciadordetarefas.model.user;

import com.example.gerenciadordetarefas.model.task.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@Data
public class User  {
    @Id
    private String id;

    private String name;

    private List<Task> assignedTasks;




}
