package com.pradip.CollaborativeTaskManagement.Repository;

import com.pradip.CollaborativeTaskManagement.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
