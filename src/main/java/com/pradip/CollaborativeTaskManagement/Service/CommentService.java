//package com.pradip.CollaborativeTaskManagement.Service;
//
//import com.pradip.CollaborativeTaskManagement.Model.Comment;
//import com.pradip.CollaborativeTaskManagement.Model.Task;
//import com.pradip.CollaborativeTaskManagement.Repository.CommentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CommentService {
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public Comment saveComment(Comment comment) {
//        return commentRepository.save(comment);
//    }
//
//    public List<Comment> findByTask(Task task) {
//        return commentRepository.findByTask(task);
//    }
//
//    public void deleteComment(Long id) {
//        commentRepository.deleteById(id);
//    }
//}
//

package com.pradip.CollaborativeTaskManagement.Service;

import com.pradip.CollaborativeTaskManagement.Model.Comment;
import com.pradip.CollaborativeTaskManagement.Model.Notification;
import com.pradip.CollaborativeTaskManagement.Model.Task;
import com.pradip.CollaborativeTaskManagement.Model.User;
import com.pradip.CollaborativeTaskManagement.Repository.CommentRepository;
import com.pradip.CollaborativeTaskManagement.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Comment saveComment(Comment comment) {
        // Save the comment
        Comment savedComment = commentRepository.save(comment);

        // Notify relevant users
        sendNotifications(savedComment);

        return savedComment;
    }

    public List<Comment> findByTask(Task task) {
        return commentRepository.findByTask(task);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private void sendNotifications(Comment comment) {
        Task task = comment.getTask();
        User author = comment.getAuthor();
        String message = "New comment on task: " + task.getTitle();

        if (task.getCreatedBy() != null && !task.getCreatedBy().equals(author)) {
            createNotification(task.getCreatedBy(), message);
        }

        if (task.getAssignedTo() != null && !task.getAssignedTo().equals(author)) {
            createNotification(task.getAssignedTo(), message);
        }
    }

    private void createNotification(User receiver, String message) {
        Notification notification = new Notification();
        notification.setReceiver(receiver);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}

