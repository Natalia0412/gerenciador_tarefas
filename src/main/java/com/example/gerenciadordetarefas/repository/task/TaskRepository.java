package com.example.gerenciadordetarefas.repository.task;

import com.example.gerenciadordetarefas.model.task.Task;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
