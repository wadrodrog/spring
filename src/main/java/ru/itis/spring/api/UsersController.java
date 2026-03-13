package ru.itis.spring.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService service;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return service.getAll();
    }

    @PostMapping
    public void saveUser(@RequestParam String name) {
        service.save(name, LocalDate.now());
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        service.deleteAll();
    }
}
