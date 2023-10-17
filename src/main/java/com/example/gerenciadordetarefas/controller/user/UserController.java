package com.example.gerenciadordetarefas.controller.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.service.user.UserService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody UserDto userDto)  {

        User user = userService.createUser(userDto);
        return  user;


    }

//    @PostMapping
//    public User addUser(@RequestBody User userDto) {
//
//        return userService.createUser(userDto);
//    }
}
