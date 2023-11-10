package com.example.gerenciadordetarefas.controller.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.dto.task.TaskDtoResponse;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDtoResponse> addTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDtoResponse taskDtoResponse = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDtoResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDtoResponse> alterTask(@RequestBody TaskDto taskDto, @PathVariable String id) {
        TaskDtoResponse taskDtoResponse = taskService.updateTask(taskDto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDtoResponse> getIndividualTask(@PathVariable String id){
        TaskDtoResponse taskDtoResponse = taskService.getTaskByIdResponse(id);
        return ResponseEntity.ok(taskDtoResponse);
    }

    @GetMapping()
    public ResponseEntity<List<TaskDtoResponse>> returnAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDtoResponse> deleteTaskById(@PathVariable String id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
