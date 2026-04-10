package ru.itis.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class UserEntity extends BaseEntity {
    private String name;
    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private RoleEnum role;
    private StateEnum state;

    public enum RoleEnum {
        USER, ADMIN
    }

    public enum StateEnum {
        ACTIVE, BANNED
    }
}
