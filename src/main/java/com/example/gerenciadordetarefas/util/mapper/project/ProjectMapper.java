package com.example.gerenciadordetarefas.util.mapper.project;

import com.example.gerenciadordetarefas.dto.project.ProjectDto;
import com.example.gerenciadordetarefas.dto.project.ProjectDtoResponse;
import com.example.gerenciadordetarefas.dto.task.TaskInfoDto;
import com.example.gerenciadordetarefas.dto.user.UserInfoDto;
import com.example.gerenciadordetarefas.model.project.Project;
import com.example.gerenciadordetarefas.model.task.Task;
import com.example.gerenciadordetarefas.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {


    public static Project projectDtoToProject(ProjectDto projectDto){
        return Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .taskList(new ArrayList<>())
                .build();
    }

    public static ProjectDtoResponse projectToProjectDtoResponse(Project project){
        return ProjectDtoResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(
                        project.getDescription()
                )
                .taskList(ProjectMapper.taskListToTaskInfoDtoList(project))
                .build();
    }

    public static List<ProjectDtoResponse> projectToProjectDtoResponse(List<Project> projects){
       return projects.stream()
                .map(project -> ProjectDtoResponse.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .description(
                                project.getDescription()
                        )
                        .taskList(ProjectMapper.taskListToTaskInfoDtoList(project))
                        .build())
                .toList();

    }

    public static List<TaskInfoDto> taskListToTaskInfoDtoList(Project project) {
        List<Task> tasks = project.getTaskList();
        return tasks.stream()
                .map(task ->
                        TaskInfoDto.builder()
                                .idTask(task.getId())
                                .title(task.getTitle())
                                .assigne(ProjectMapper.userToUserInfoDtoList(task.getAssigne()))
                                .build()
                )
                .toList();
    }

    public static List<UserInfoDto> userToUserInfoDtoList(List<User> user) {
        return user.stream()
                .map(u-> UserInfoDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .build()
                ).toList();

    }

}
