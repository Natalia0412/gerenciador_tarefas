package com.example.gerenciadordetarefas.controller.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/department")
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public Department addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.createDepartment(departmentDto);
    }
    @PutMapping("/{id}")
    public Department updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable String id){
        return departmentService.updateDepartment(departmentDto, id);
    }
}
