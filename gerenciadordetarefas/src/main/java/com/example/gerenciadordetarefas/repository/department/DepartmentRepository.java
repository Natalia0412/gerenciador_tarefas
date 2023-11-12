package com.example.gerenciadordetarefas.repository.department;

import com.example.gerenciadordetarefas.model.department.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

}

