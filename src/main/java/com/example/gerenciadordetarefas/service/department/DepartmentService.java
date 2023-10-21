package com.example.gerenciadordetarefas.service.department;

import com.example.gerenciadordetarefas.dto.department.DepartmentDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.department.DepartmentRepository;
import com.example.gerenciadordetarefas.service.user.UserService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.department.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserService userService;

    public Department createDepartment(DepartmentDto dto){
        Department department =  DepartmentMapper.departmentDtoToDepartment(dto);
        if(dto.getUsers()!=null){
            this.addToUsers(dto, department);
        }

        return departmentRepository.save(department);
    }

    public Department updateDepartment(DepartmentDto dto, String id) {
        this.getDepartmentById(id);
        Department department =  DepartmentMapper.departmentDtoToDepartment(dto);
        this.addToUsers(dto,department);
        department.setId(id);
        return departmentRepository.save(department);
    }

    public Department addToUsers(DepartmentDto dto, Department department){
        List<String> idsUsers = dto.getUsers();
        idsUsers.forEach(id -> {
            User user = userService.getUserById(id);
            department.getUsers().add(user);
        });
        return department;
    }


    public  Department getDepartmentById(String id)  {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()) throw new ResourceNotFoundException("Department not found with id: ", id);
        return  department.get();
    }
}
