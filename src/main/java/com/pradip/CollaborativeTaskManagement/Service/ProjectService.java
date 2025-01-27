package com.pradip.CollaborativeTaskManagement.Service;

import com.pradip.CollaborativeTaskManagement.Model.Project;
import com.pradip.CollaborativeTaskManagement.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project); // Saves the project in the database
    }
    public Project getProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll(); // Retrieves all projects
    }
}

