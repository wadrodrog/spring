package ru.itis.spring.persistence.repository;

import ru.itis.spring.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {}
