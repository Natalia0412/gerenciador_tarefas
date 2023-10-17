package com.example.gerenciadordetarefas.util.mapper.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.user.User;

public class UserMapper {

    public static User userDtoToUSer(UserDto userDto, Department department) {

       return User.builder()
               .name(userDto.getName())
               .department(department)
               .build();
    }

//    public static UserDto userToUserDto(User user){
//        return UserDto.builder()
//                .name(user.getName())
//                .department(user.getDepartment())
//                .build();
//    }



}
