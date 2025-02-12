package com.pradip.CollaborativeTaskManagement.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketNotificationController {

    @MessageMapping("/notify") // When the client sends a message to /app/notify
    @SendTo("/topic/notifications") // Send message to all subscribers
    public String sendNotification(String message) {
        return message; // Broadcast message to all connected clients
    }
}
