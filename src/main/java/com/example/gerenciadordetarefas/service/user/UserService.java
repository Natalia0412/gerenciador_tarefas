package com.example.gerenciadordetarefas.service.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.user.UserRepository;
import com.example.gerenciadordetarefas.service.task.TaskService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TaskService taskService;





    public  User createUser(UserDto userDto)  {
        User user = UserMapper.userDtoToUser(userDto);
        if(userDto.getAssignedTasks() != null){
            this.addToTaskList(userDto, user);
        }
        userRepository.save(user);
        return user;
    }

    public User updateUser(UserDto userDto, String id){
        this.getUserById(id);
        User user = UserMapper.userDtoToUser(userDto);
        this.addToTaskList(userDto,user);
        user.setId(id);
        userRepository.save(user);
        return user;
    }




    public User getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found, id: ", id));
        return user;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User addToTaskList(UserDto userDto, User user){
        List<String>assignedTasks =  userDto.getAssignedTasks();
        assignedTasks.forEach(id-> {
            Task task = taskService.getTaskById(id);
            user.getAssignedTasks().add(task);
        });
        return user;
    }

    public void deleteUserById(String id){
        this.getUserById(id);
        userRepository.deleteById(id);
    }


    public User checkIfUserAlreadyHasATask(UserDto userDto, String id) {
        User user = this.getUserById(id);

        for (String dtoTaskId : userDto.getAssignedTasks()) {
            for (Task task : user.getAssignedTasks()) {
                if (task.getId().equals(dtoTaskId)) {
                    throw new RuntimeException("Task já está associada a usuário");
                }
            }
        }
        return  this.addToTaskList(userDto, user);

    }
}




