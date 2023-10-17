package com.example.gerenciadordetarefas.model.department;

import com.example.gerenciadordetarefas.model.user.User;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Department  {
    @Id
    private String id;
    private String name;
    private String description;
    private List<User> userList = new ArrayList<>();

    public Department(){}

    public  Department(String id, String name, String description, List<User> userList){
        this.id = id;
        this.name= name;
        this.description = description;
        this.userList = userList;
    }

    public String getId(){
        return id;
    }
    public  void setId(String id) {
        this.id = id;
    }
    public String getName(){
        return  name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Users='" + userList + '\'' +
                '}';
    }
}
