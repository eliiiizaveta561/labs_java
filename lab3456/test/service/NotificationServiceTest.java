package org.example.service;

import org.example.model.entity.Notification;
import org.example.model.entity.User;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldGetNotificationById() {
        Long notificationId = 1L;

        User user = new User();
        user.setId(1L);

        Notification mockNotification = new Notification();
        mockNotification.setId(notificationId);
        mockNotification.setTitle("Тестовое уведомление");
        mockNotification.setRecipient(user);

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        Notification result = notificationService.getNotificationById(notificationId);

        assertNotNull(result);
        assertEquals(notificationId, result.getId());
        assertEquals("Тестовое уведомление", result.getTitle());

        verify(notificationRepository, times(1)).findById(notificationId);
    }

    @Test
    void shouldThrowExceptionWhenNotificationNotFound() {
        Long notificationId = 999L;

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> notificationService.getNotificationById(notificationId));
    }
}
