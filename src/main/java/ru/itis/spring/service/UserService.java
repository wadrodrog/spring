package ru.itis.spring.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.UserRepository;

import java.time.LocalDate;

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
                .build();

        repository.save(user);
    }

    public UserEntity get(long id) {
        return repository.getById(id).orElse(null);
    }
}
