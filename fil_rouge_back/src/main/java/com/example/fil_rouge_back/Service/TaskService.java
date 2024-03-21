package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import com.example.fil_rouge_back.Model.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    public TaskRepository repo;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.repo = taskRepository;
    }

    // Récupération de toutes les tâches
    public List<TaskEntity> getAllTasks() {
        return this.repo.findAll();
    }

    // Récupération d'une tache grâce à son id
    public Optional<TaskEntity> getTaskById(Long id) {
        return this.repo.findById(id);
    }

    // Récupérer une tâche grâce à son nom
    public Optional<TaskEntity> findByTitle(String title) {
        return this.repo.findByTitle(title);
    }

    // Création d'une tâche
    public TaskEntity createTask(TaskEntity data) {
        return this.repo.save(data);
    }

    // Modification d'une tâche
    public TaskEntity updateTask(Long id, TaskEntity data) {
        TaskEntity task = this.repo.findById(id).get();
        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setStatus(data.getStatus());
        task.setEstimationHours(data.getEstimationHours());

        return this.createTask(task);
    }

    // Suppression d'une tâche
    public void deleteTask(Long id) {
        this.repo.deleteById(id);
    }

}
