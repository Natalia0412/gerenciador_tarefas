package com.example.gerenciadordetarefas.model.task;

import com.example.gerenciadordetarefas.model.user.User;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.util.List;

@Builder
@Document
@Data
public class Task {

    private String id;
    private String title;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private String priority;
    private List<User> assigne;
}