package com.example.gerenciadordetarefas.repository.task;

import com.example.gerenciadordetarefas.model.task.Task;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{_id: {$in:?0}})")
    List<Task> findByIds(List<String> ids);
}
