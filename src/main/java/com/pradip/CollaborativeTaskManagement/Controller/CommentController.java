package com.pradip.CollaborativeTaskManagement.Controller;

import com.pradip.CollaborativeTaskManagement.Model.Comment;
import com.pradip.CollaborativeTaskManagement.Service.CommentService;
import com.pradip.CollaborativeTaskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/add/{taskId}")
    public ResponseEntity<?> addComment(@PathVariable Long taskId, @RequestBody Comment comment) {
        return taskService.findById(taskId)
                .map(task -> {
                    comment.setTask(task);
                    Comment savedComment = commentService.saveComment(comment);
                    return ResponseEntity.ok(savedComment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getCommentsByTask(@PathVariable Long taskId) {
        return taskService.findById(taskId)
                .map(task -> ResponseEntity.ok(commentService.findByTask(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully.");
    }
}

