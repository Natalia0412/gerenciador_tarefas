package com.example.gerenciadordetarefas.util.mapper.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;

import java.util.ArrayList;

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


}
