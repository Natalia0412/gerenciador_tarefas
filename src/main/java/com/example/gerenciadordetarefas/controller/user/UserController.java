package com.example.gerenciadordetarefas.controller.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.dto.user.UserDtoResponse;
import com.example.gerenciadordetarefas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDto userDto)  {
        UserDtoResponse userDtoResponse = userService.createUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> alterUser(@RequestBody UserDto userDto, @PathVariable String id){
        UserDtoResponse userDtoResponse = userService.updateUser(userDto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getByIdUser(@PathVariable String id){
        UserDtoResponse userDtoResponse = userService.getUserByIdResponse(id);
        return ResponseEntity.ok().body(userDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>getUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
