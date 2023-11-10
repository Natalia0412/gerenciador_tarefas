package com.example.gerenciadordetarefas.service.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.dto.task.TaskDtoResponse;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.task.TaskRepository;
import com.example.gerenciadordetarefas.service.user.UserService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.project.ProjectMapper;
import com.example.gerenciadordetarefas.util.mapper.task.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private final UserService userService;
    @Autowired
    public TaskService(@Lazy UserService userService) {
        this.userService = userService;
    }



    public TaskDtoResponse createTask(TaskDto taskDto) {
        Task task = TaskMapper.taskDtoToTask(taskDto);
        this.addToAssigne(taskDto, task);
        taskRepository.save(task);
        return TaskMapper.taskToTaskDtoResponse(task);
    }

    public TaskDtoResponse updateTask(TaskDto dto, String id) {
        this.getTaskById(id);
        Task task = TaskMapper.taskDtoToTask(dto);
        this.addToAssigne(dto, task);
        task.setId(id);
        taskRepository.save(task);
        return TaskMapper.taskToTaskDtoResponse(task);
    }

    public Task addToAssigne(TaskDto taskDto, Task task){
        List<String>assigne = taskDto.getAssigne();
        assigne.forEach(id -> {
            User user = userService.getUserById(id);
            task.getAssigne().add(user);
        });
        return task;
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found, id: ", id));
    }

    public TaskDtoResponse getTaskByIdResponse(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found, id: ", id));
        return TaskMapper.taskToTaskDtoResponse(task);
    }

    public List<TaskDtoResponse> getAllTasks() {
        return TaskMapper.taskToTaskDtoResponse(taskRepository.findAll());
    }

    public void deleteTask(String id){
        this.getTaskById(id);
        taskRepository.deleteById(id);
    }


}
