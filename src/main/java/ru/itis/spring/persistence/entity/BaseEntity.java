package ru.itis.spring.persistence.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    LocalDateTime lastActedAt;

    @Column
    LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (lastActedAt == null) {
            lastActedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        setLastActedAt(LocalDateTime.now());
    }

    @PreDestroy
    protected void preDestroy() {
        setDeletedAt(LocalDateTime.now());
    }
}