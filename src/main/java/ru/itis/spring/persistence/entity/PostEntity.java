package ru.itis.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity {
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
}
