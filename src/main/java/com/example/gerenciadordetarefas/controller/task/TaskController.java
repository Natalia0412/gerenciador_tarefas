package com.example.gerenciadordetarefas.controller.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task addTask(@Valid @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }
    @PutMapping("/{id}")
    public Task alterTask(@RequestBody TaskDto taskDto, @PathVariable String id) {
        return taskService.updateTask(taskDto,id);
    }

    @GetMapping("/{id}")
    public Task getIndividualTask(@PathVariable String id){
        return taskService.getTaskById(id);
    }
}
