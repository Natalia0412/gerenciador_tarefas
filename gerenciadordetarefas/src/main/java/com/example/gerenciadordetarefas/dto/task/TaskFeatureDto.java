package com.example.gerenciadordetarefas.dto.task;

import com.example.gerenciadordetarefas.enums.task.FeatureType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;


public class TaskFeatureDto{
    private FeatureType featureType;
}
