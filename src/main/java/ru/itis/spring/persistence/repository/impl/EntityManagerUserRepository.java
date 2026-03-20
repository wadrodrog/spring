package ru.itis.spring.persistence.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.EntityManagerRepository;

import java.util.List;

@Repository
public class EntityManagerUserRepository implements EntityManagerRepository<UserEntity, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    @Override
    public UserEntity getById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> getAll() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void delete(UserEntity user) {
        entityManager.remove(user);
    }
}
