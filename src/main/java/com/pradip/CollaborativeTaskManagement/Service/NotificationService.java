package com.pradip.CollaborativeTaskManagement.Service;

import com.pradip.CollaborativeTaskManagement.Model.Notification;
import com.pradip.CollaborativeTaskManagement.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // WebSocket messaging

    public Notification sendNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        messagingTemplate.convertAndSend("/topic/notifications", savedNotification);
        return savedNotification;
    }
}
