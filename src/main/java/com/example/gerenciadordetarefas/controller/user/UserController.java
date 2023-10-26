package com.example.gerenciadordetarefas.controller.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public User alterUser(@RequestBody UserDto userDto, @PathVariable String id){
        return userService.updateUser(userDto,id);
    }

    @GetMapping("/{id}")
    public User getByIdUser(@PathVariable String id){
        //System.out.println(id);
        return userService.getUserById(id);
    }


    @GetMapping
    public List<User> getUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
    }
//    @PostMapping
//    public User addUser(@RequestBody User userDto) {
//
//        return userService.createUser(userDto);
//    }
}
