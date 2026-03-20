package ru.itis.spring.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.PostEntity;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

}
