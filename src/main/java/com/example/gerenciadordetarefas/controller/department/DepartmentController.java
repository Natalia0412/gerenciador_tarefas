package com.example.gerenciadordetarefas.controller.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.dto.department.DepartmentDtoResponse;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/department")
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDtoResponse> addDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDtoResponse departmentDtoResponse = departmentService.createDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDtoResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDtoResponse> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable String id){
        DepartmentDtoResponse departmentDtoResponse = departmentService.updateDepartment(departmentDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDtoResponse> getDepartmentId(@PathVariable String id){
        DepartmentDtoResponse departmentDtoResponse = departmentService.getDepartmentByIdResponse(id);
        return ResponseEntity.ok(departmentDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDtoResponse>> returnAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
