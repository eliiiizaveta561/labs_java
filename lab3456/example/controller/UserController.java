package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.service.UserMapper;
import org.example.model.entity.User;
import org.example.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public UserDto createUser(@RequestBody @Valid UserDto request) {
        User response = userService.createUser(request);
        return UserDto.builder()
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    private final UserMapper userMapper;
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User response = userService.getUserById(id);
        return UserDto.builder()
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid
    UserDto request) {
        User response = userService.updateUser(id, request);
        return UserDto.builder()
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return String.format("Пользователь %s удален", id);
    }


}
