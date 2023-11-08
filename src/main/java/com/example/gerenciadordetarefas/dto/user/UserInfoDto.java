package com.example.gerenciadordetarefas.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Builder
@Data
public class UserInfoDto {
    private String id;
    private String name;
}
