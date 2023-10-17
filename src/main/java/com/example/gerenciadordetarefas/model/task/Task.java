package com.example.gerenciadordetarefas.model.task;

import com.example.gerenciadordetarefas.model.department.Department;
import com.example.gerenciadordetarefas.model.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public class Task {
    @Id
    private  String id;
    private String title;
    private  String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private String priority;
    private User assigne;
    private Department department;
    public Task(){}

    public Task(String id, String title, String description, LocalDate dateOfCreation, LocalDate dueDate, String priority, User assigne, Department department) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.dueDate = dueDate;
        this.priority = priority;
        this.assigne = assigne;
        this.department = department;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getAssigne() {
        return assigne;
    }

    public void setAssigne(User assigne) {
        this.assigne = assigne;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dateOfCreation, task.dateOfCreation) && Objects.equals(dueDate, task.dueDate) && Objects.equals(priority, task.priority) && Objects.equals(assigne, task.assigne) && Objects.equals(department, task.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dateOfCreation, dueDate, priority, assigne, department);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                ", assigne=" + assigne +
                ", department=" + department +
                '}';
    }
}
