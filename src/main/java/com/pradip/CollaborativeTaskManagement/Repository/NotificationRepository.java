package com.pradip.CollaborativeTaskManagement.Repository;

import com.pradip.CollaborativeTaskManagement.Model.Notification;
import com.pradip.CollaborativeTaskManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndSeenFalse(User user);
}

