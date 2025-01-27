package com.pradip.CollaborativeTaskManagement.Service;


import com.pradip.CollaborativeTaskManagement.Model.Project;
import com.pradip.CollaborativeTaskManagement.Model.Task;
import com.pradip.CollaborativeTaskManagement.Model.User;
import com.pradip.CollaborativeTaskManagement.Repository.ProjectRepository;
import com.pradip.CollaborativeTaskManagement.Repository.TaskRepository;
import com.pradip.CollaborativeTaskManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Task saveTask(Task task, Long assignedToId, Long projectId, Long createdById) {
        User assignedTo = userRepository.findById(assignedToId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + assignedToId));
        User createdBy = userRepository.findById(createdById)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + createdById));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

        task.setAssignedTo(assignedTo);
        task.setCreatedBy(createdBy);
        task.setProject(project);

        return taskRepository.save(task);
    }

    public List<Task> findByAssignedTo(User user) {
        return taskRepository.findByAssignedTo(user);
    }

    public List<Task> findByCreatedBy(User user) {
        return taskRepository.findByCreatedBy(user);
    }

    public List<Task> findByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

