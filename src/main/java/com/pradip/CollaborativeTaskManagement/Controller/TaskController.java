package com.pradip.CollaborativeTaskManagement.Controller;

import com.pradip.CollaborativeTaskManagement.Model.Task;
import com.pradip.CollaborativeTaskManagement.Service.TaskService;
import com.pradip.CollaborativeTaskManagement.Service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Task> saveTask(@RequestBody Map<String, Object> requestBody) {
        String title = (String) requestBody.get("title");
        String description = (String) requestBody.get("description");
        Task.TaskStatus status = Task.TaskStatus.valueOf((String) requestBody.get("status"));
        Long assignedToId = ((Number) requestBody.get("assignedTo")).longValue();
        Long projectId = ((Number) requestBody.get("project")).longValue();
        Long createdById = ((Number) requestBody.get("createdBy")).longValue();
        String dueDateStr = (String) requestBody.get("dueDate");

        LocalDate dueDate = LocalDate.parse(dueDateStr);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDate);

        Task createdTask = taskService.saveTask(task, assignedToId, projectId, createdById);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<?> getTasksAssignedTo(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(taskService.findByAssignedTo(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/created/{userId}")
    public ResponseEntity<?> getTasksCreatedBy(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(taskService.findByCreatedBy(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully.");
    }
}

