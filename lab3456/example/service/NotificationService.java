package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.NotificationDto;
import org.example.model.entity.Notification;
import org.example.model.entity.User;
import org.example.model.enums.NotificationChannel;
import org.example.model.enums.NotificationStatus;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Notification createNotification(NotificationDto request) {
        User user = userRepository.findById(request.getRecipientId()).orElseThrow();

        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());
        notification.setStatus(NotificationStatus.CREATED);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRecipient(user);
        notificationRepository.save(notification);

//        if (true) {
//            throw new RuntimeException("Искусственная ошибка");
//        }
        return notification;
    }


    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Notification updateNotification(Long id, NotificationDto request) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());

        if (request.getStatus() == NotificationStatus.SENT && notification.getSentAt() == null) {
            notification.setSentAt(LocalDateTime.now());
        }

        notification.setStatus(request.getStatus());
        return notificationRepository.save(notification);
    }


    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notificationRepository.delete(notification);
    }
    public List<Notification> getNotificationsByStatus(NotificationStatus
                                                               status) {
        return notificationRepository.findByStatus(status);
    }
    public List<Notification> getNotificationsByChannel(NotificationChannel
                                                                channel) {
        return notificationRepository.findByChannel(channel);
    }
    public List<Notification> getNotificationsByRecipientId(Long
                                                                    recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    public List<Notification> getByStatusAndChannel(NotificationStatus status, NotificationChannel channel) {
        return notificationRepository.findByStatusAndChannel(status, channel);
    }

    public List<Notification> getByStatusOrderByCreatedAtAsc(NotificationStatus status) {
        return notificationRepository.findByStatusOrderByCreatedAtAsc(status);
    }

    public List<Notification> getAllNotificationsSortedByDateDesc() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }


    public List<Notification> getByRecipientIdAndStatus(Long recipientId, NotificationStatus status) {
        return notificationRepository.findByRecipientIdAndStatus(recipientId, status);
    }



}

