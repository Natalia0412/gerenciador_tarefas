package com.example.gerenciadordetarefas.util.mapper.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;

public class DepartmentMapper {

    public static Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .name(departmentDto.getName())
                .description(departmentDto.getDescription())
                .userList(departmentDto.getUsers())
                .build();
    }

    public static DepartmentDto departmentToDepartmentDto(Department department){
        return DepartmentDto.builder()
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }


}
