package ru.itis.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class UserServiceProxy {
    private UserService userService;

    public void save(String name, LocalDate birthDate) {
        log.info("before...");
        userService.save(name, birthDate);
        log.info("after...");
    }
}
