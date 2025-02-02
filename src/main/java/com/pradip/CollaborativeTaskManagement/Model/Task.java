package com.pradip.CollaborativeTaskManagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; // e.g., TODO, IN_PROGRESS, COMPLETED

//    @ManyToOne
//    @JoinColumn(name = "assigned_to_id")
//    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "id")
    @JsonManagedReference("task-assigned")
    private User assignedTo;  // This will be deserialized as a full User object from the `assignedTo` id


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate dueDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by_id", nullable = false)
//    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonManagedReference("task-createdBy")
    private User createdBy;



    //Enum
    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        COMPLETED
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

