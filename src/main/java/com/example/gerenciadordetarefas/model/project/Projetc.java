package com.example.gerenciadordetarefas.model.project;

import com.example.gerenciadordetarefas.model.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Projetc {
    private String name;
    private String description;
    private List<Task> tasks = new ArrayList<>();

    public Projetc (String name, String description){
        this.name= name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDescription(){
        return  description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
