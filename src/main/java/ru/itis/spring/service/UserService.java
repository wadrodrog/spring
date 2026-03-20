package ru.itis.spring.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.spring.persistence.entity.Status;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.JpaUserRepository;
import ru.itis.spring.persistence.repository.UserRepository;
import ru.itis.spring.persistence.repository.impl.EntityManagerUserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository jdbcRepository;
    private final JpaUserRepository jpaRepository;
    private final EntityManagerUserRepository emRepository;

    public void save(String name, LocalDate birthDate) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        UserEntity user = UserEntity.builder()
                .name(name)
                .birthDate(birthDate)
                .status(Status.REGISTERED)
                .build();

        emRepository.save(user);
    }

    public UserEntity get(long id) {
        return emRepository.getById(id);
    }

    public List<UserEntity> getAll() {
        return emRepository.getAll();
    }

    public UserEntity delete(long id) {
        UserEntity user = emRepository.getById(id);
        if (user != null) {
            emRepository.delete(user);
        }
        return user;
    }

    public void deleteAll() {
        //emRepository.deleteAll();
    }
}
