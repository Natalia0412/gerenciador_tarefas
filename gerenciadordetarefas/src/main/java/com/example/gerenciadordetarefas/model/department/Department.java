package com.example.gerenciadordetarefas.model.department;

import com.example.gerenciadordetarefas.model.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Department  {
    @Id
    private String id;
    private String name;
    private String description;
    private List<User> users ;


}
