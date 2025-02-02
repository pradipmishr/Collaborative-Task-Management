package com.pradip.CollaborativeTaskManagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // e.g., ADMIN, MEMBER

    @ManyToMany(mappedBy = "members")
    private List<Project> projects;



    @OneToMany(mappedBy = "assignedTo")
    @JsonBackReference("task-assigned")
    private Set<Task> tasksAssigned;

    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference("task-createdBy")
    private Set<Task> tasksCreated;

    public User(Long id) {
        this.id = id;
    }

    //Enum
    public enum Role {
        ADMIN,
        MEMBER
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Set<Task> getTasksAssigned() {
        return tasksAssigned;
    }
    public void setTasksAssigned(Set<Task> tasksAssigned) {
        this.tasksAssigned = tasksAssigned;
    }

}

