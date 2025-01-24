package com.pradip.CollaborativeTaskManagement.Repository;

import com.pradip.CollaborativeTaskManagement.Model.Comment;
import com.pradip.CollaborativeTaskManagement.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task); // Fetch all comments for a specific task.
}

