package com.example.gerenciadordetarefas.service.user;

import com.example.gerenciadordetarefas.dto.user.UserDto;
import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.user.User;
import com.example.gerenciadordetarefas.repository.user.UserRepository;
import com.example.gerenciadordetarefas.service.department.DepartmentService;
import com.example.gerenciadordetarefas.util.exception.ResourceNotFoundException;
import com.example.gerenciadordetarefas.util.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired private DepartmentService departmentService;





    public  User createUser(UserDto userDto)  {

        Department  department = this.foundDepartment(userDto.getDepartment());
        User user = UserMapper.userDtoToUSer(userDto, department);
        userRepository.save(user);


        return user;
    }

    public User getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found, id: ", id));
        System.out.println(user);
        return user;
    }

    public Department foundDepartment(String id) {
        return departmentService.returnDepartment(id);
    }

}




