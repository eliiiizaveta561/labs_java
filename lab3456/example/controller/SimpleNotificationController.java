package org.example.controller;


import org.example.service.NotificationManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleNotificationController {
    private final NotificationManager notificationManager;

    public SimpleNotificationController(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @GetMapping("/notify")
    public String notify(@RequestParam String message, @RequestParam String email) {
        notificationManager.notify(message, email);
        return "Уведомление отправлено через Java Config";
    }
}
