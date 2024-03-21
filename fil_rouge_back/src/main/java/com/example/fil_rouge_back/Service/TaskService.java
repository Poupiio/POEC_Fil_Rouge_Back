package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.Entity.Task;
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
    public List<Task> getAllTasks() {
        return this.repo.findAll();
    }

    // Récupération d'une tache grâce à son id
    public Optional<Task> getTaskById(Long id) {
        return this.repo.findById(id);
    }

    // Création d'une tâche
    public Task createTask(Task data) {
        return this.repo.save(data);
    }

    // Modification d'une tâche
    

    // Suppression d'une tâche
    public void deleteTask(Long id) {
        this.repo.deleteById(id);
    }

}
