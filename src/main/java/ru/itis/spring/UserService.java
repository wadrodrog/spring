package ru.itis.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public long register(String name) {
        name = name.trim();
        repository.create(name);
        return repository.read(name).getUserId();
    }

    public UserEntity get(long userId) {
        return repository.read(userId);
    }

    public void updateUsername(long userId, String name) {
        repository.update(userId, name);
    }

    public void remove(long userId) {
        repository.delete(userId);
    }
}
