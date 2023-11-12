package com.example.gerenciadordetarefas.dto.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
public class UserDtoResponse extends UserDto {
    private String id;
}





