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
    private final UserRepository userRepository;

    public void save(String name, LocalDate birthDate) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        UserEntity user = UserEntity.builder()
                .name(name)
                .birthDate(birthDate)
                .status(Status.REGISTERED)
                .build();

        userRepository.save(user);
    }

    public UserEntity get(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity delete(long id) {
        UserEntity user = get(id);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
