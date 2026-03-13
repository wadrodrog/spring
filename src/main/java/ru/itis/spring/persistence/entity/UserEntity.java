package ru.itis.spring.persistence.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Status status;
}
