package com.example.fil_rouge_back.Model.Repository;

import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByTitle(String title);

    // Fonction pour récupérer les tâches associées à un projet spécifique (id)
    List<TaskEntity> findAllTasksByProjectId(Long projectId);
}

