package com.example.gerenciadordetarefas.util.mapper.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.dto.user.UserDtoResponse;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User userDtoToUser(UserDto userDto) {
       return User.builder()
               .name(userDto.getName())
               .assignedTasks(new ArrayList<>())
               .build();
    }
    
    public static UserDtoResponse userToUserDtoResponse(User user){
        return UserDtoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .assignedTasks(idtaskList(user))
                .build();

    }

    public static List<String> idtaskList(User user) {
        return user.getAssignedTasks().stream().map(Task::getId).collect(Collectors.toList());
    }





}
