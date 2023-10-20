package com.example.gerenciadordetarefas.util.mapper.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User userDtoToUser(UserDto userDto) {
       return User.builder()
               .name(userDto.getName())
               .assignedTasks(new ArrayList<>())
               .build();
    }

//    public static UserDto userToUserDto(User user){
//        return UserDto.builder()
//                .name(user.getName())
//                .department(user.getDepartment())
//                .build();
//    }





}
