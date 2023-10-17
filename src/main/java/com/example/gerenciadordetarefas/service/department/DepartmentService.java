package com.example.gerenciadordetarefas.service.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.repository.department.DepartmentRepository;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.department.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDto createDepartment(DepartmentDto dto){

       Department department = departmentRepository.save(DepartmentMapper.departmentDtoToDepartment(dto));
       return DepartmentMapper.departmentToDepartmentDto(department);
    }

//    public  Department returnDepartment(String id)  {
//        Department department  = departmentRepository.findById(id)
//                .orElseThrow(() ->  new ResourceNotFoundException("Department not found ", id));
//        return  department;
//    }

    public  Department returnDepartment(String id)  {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()) throw new ResourceNotFoundException("Department not found with id: ", id);
        return  department.get();
    }
}
