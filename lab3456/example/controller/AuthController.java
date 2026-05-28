package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.RegisterRequest;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return "Пользователь зарегистрирован";
    }

    @PostMapping("/register/admin")
    public String registerAdmin(@Valid @RequestBody RegisterRequest request) {
        authService.registerAdmin(request);
        return "Администратор зарегистрирован";
    }
}
