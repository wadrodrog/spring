package ru.itis.spring.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;
import ru.itis.spring.persistence.dto.UserDto;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public RedirectView register(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userService.save(username, passwordEncoder.encode(password));
        } catch (IllegalArgumentException e) {
            return new RedirectView("/register");
        }
        return new RedirectView("/");
    }
}
