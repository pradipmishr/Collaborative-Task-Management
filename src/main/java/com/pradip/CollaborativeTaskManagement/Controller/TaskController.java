package com.pradip.CollaborativeTaskManagement.Controller;

import com.pradip.CollaborativeTaskManagement.Model.Task;
import com.pradip.CollaborativeTaskManagement.Service.TaskService;
import com.pradip.CollaborativeTaskManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

@PostMapping("/admin/create")  // Endpoint to create a new task
public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task savedTask = taskService.saveTask(task);  // Calls the service to save the task
    return new ResponseEntity<>(savedTask, HttpStatus.CREATED);  // Returns the saved task with HTTP 201 status
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

