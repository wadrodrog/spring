package ru.itis.spring.persistence.repository;

import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.UserEntity;

import java.util.*;

@Repository
public class InMemoryRepository {
    private final Map<UUID, UserEntity> storage = new HashMap<>();

    public void save(UserEntity user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
            storage.put(user.getId(), user);
        } else {
            Optional.ofNullable(storage.get(user.getId()))
                    .map(existed -> {
                        existed.setName(user.getName());
                        existed.setBirthDate(user.getBirthDate());
                        return existed;
                    });
        }
    }

    public UserEntity get(UUID id) {
        return storage.getOrDefault(id, null);
    }

    public boolean delete(UUID id) {
        return storage.remove(id) != null;
    }
}
