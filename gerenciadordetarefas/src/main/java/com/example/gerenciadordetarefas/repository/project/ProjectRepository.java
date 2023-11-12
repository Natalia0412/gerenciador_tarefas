package com.example.gerenciadordetarefas.repository.project;

import com.example.gerenciadordetarefas.model.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
