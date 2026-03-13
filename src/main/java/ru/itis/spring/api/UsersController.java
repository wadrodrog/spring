package ru.itis.spring.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService service;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public void saveUser(@RequestParam String name) {
        service.save(name, LocalDate.now());
    }
}
