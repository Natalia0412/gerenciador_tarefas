package com.example.gerenciadordetarefas.service.task;

import com.example.gerenciadordetarefas.dto.task.TaskDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.task.TaskRepository;
import com.example.gerenciadordetarefas.service.department.DepartmentService;
import com.example.gerenciadordetarefas.service.user.UserService;
import com.example.gerenciadordetarefas.util.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    public Task createTask(TaskDto taskDto) {
        User user = userService.getUserById(taskDto.getAssigne());
        Department  department = departmentService.returnDepartment(taskDto.getDepartment());
        Task task = TaskMapper.taskDtoToTask(taskDto,department, user);
        return taskRepository.save(task);
    }

    public Department foundDepartment(String id) {
        return departmentService.returnDepartment(id);
    }
}
