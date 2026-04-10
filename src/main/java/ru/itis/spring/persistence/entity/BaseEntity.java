package ru.itis.spring.persistence.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("now()")
    LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("now()")
    LocalDateTime lastActedAt;

    @Column
    LocalDateTime deletedAt;

    @PreUpdate
    protected void preUpdate() {
        setLastActedAt(LocalDateTime.now());
    }

    @PreDestroy
    protected void preDestroy() {
        setDeletedAt(LocalDateTime.now());
    }
}