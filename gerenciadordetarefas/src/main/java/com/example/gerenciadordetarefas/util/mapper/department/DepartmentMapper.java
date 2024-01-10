package com.example.gerenciadordetarefas.util.mapper.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.dto.department.DepartmentDtoResponse;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .name(departmentDto.getName())
                .description(departmentDto.getDescription())
                .users(new ArrayList<>())
                .build();
    }

    public static DepartmentDto departmentToDepartmentDto(Department department){
        return DepartmentDto.builder()
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }

    public static DepartmentDtoResponse departmentToDepartmentDtoResponse(Department department){
        List<User> users = department.getUsers();
        List<UserInfoDto> usersList = users.stream()
                .map(user -> UserInfoDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build()
                )
                .toList();
        return DepartmentDtoResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .users(usersList)
                .build();
    }

    public static List<DepartmentDtoResponse> departmentToDepartmentDtoResponse(List<Department> departments){
        return  departments.stream()
                .map(department -> {
                    List<User> users = department.getUsers();
                    List<UserInfoDto> usersList = users.stream()
                            .map(user -> UserInfoDto.builder()
                                    .id(user.getId())
                                    .name(user.getName())
                                    .build()
                            )
                            .toList();
                    return DepartmentDtoResponse.builder()
                            .name(department.getName())
                            .description(department.getDescription())
                            .users(usersList)
                            .build();
                }).collect(Collectors.toList());
    }

}
