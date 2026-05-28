package org.example.service;

import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .deviceToken(user.getDeviceToken())
                .telegramChatId(user.getTelegramChatId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
