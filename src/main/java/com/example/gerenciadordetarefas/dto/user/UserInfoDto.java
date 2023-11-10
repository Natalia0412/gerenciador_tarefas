package com.example.gerenciadordetarefas.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto {
    private String id;
    private String name;
}
