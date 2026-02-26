package ru.itis.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @PostMapping
    public void saveUser(@RequestParam String name) {
        service.save(name, LocalDate.now());
    }
}
