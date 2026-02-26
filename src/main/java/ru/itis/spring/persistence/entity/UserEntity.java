package ru.itis.spring.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    private UUID id;
    private String name;
    private LocalDate birthDate;
}
