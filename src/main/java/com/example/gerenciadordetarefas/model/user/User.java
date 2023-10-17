package com.example.gerenciadordetarefas.model.user;

import com.example.gerenciadordetarefas.model.department.Department;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
@Builder
public class User implements Serializable {
    private static final long serialVersionUID =1L;
    @Id
    private String id;
    private String name;
    private Department department;
    //private List<Task> taskList = new ArrayList<>();

    public  User(){}
    public User( String id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

//    public List<Task> getTaskList(){
//        return taskList;
//    }
//
//   public void  setTaskList(String id) {
//        //this.taskList.add();
    //}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(department, user.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Department ='" + department + '\'' +
                '}';
    }
}
