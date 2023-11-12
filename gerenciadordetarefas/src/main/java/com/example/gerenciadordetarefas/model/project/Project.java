package com.example.gerenciadordetarefas.model.project;

import com.example.gerenciadordetarefas.model.task.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
public class Project {

    @Id
    private String id;
    private String name;
    private String description;
    private List<Task> taskList;



}
