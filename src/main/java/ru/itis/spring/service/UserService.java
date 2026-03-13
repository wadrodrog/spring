package ru.itis.spring.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.spring.persistence.entity.Status;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void save(String name, LocalDate birthDate) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        UserEntity user = UserEntity.builder()
                .name(name)
                .birthDate(birthDate)
                .status(Status.REGISTERED)
                .build();

        repository.save(user);
    }

    public UserEntity get(long id) {
        return repository.getById(id).orElse(null);
    }

    public List<UserEntity> getAll() {
        return repository.getAll();
    }

    public UserEntity delete(long id) {
        UserEntity user = repository.getById(id).orElse(null);
        if (repository.deleteById(id)) {
            return user;
        }
        return null;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
