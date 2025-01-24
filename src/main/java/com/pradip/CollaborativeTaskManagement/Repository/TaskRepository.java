package com.pradip.CollaborativeTaskManagement.Repository;


import com.pradip.CollaborativeTaskManagement.Model.Task;
import com.pradip.CollaborativeTaskManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user); // Fetch tasks assigned to a specific user.
    List<Task> findByCreatedBy(User user); // Fetch tasks created by a specific user.
    List<Task> findByStatus(String status); // Fetch tasks by their status (e.g., OPEN, IN_PROGRESS, DONE).
}

