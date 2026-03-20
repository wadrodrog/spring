package ru.itis.spring.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
