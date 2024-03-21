package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import com.example.fil_rouge_back.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    // Récupérer toutes les tâches
    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    // Récupérer une tâche grâce à son id
    @GetMapping("/{id}")
    public Optional<TaskEntity> getTaskById(@PathVariable Long id) {
        return this.taskService.getTaskById(id);
    }

    // Récupérer une tâche grâce à son nom
    @GetMapping("/title/{title}")
    public Optional<TaskEntity> findByTitle(@PathVariable String title) {
        return taskService.findByTitle(title);
    }

    // Créer une nouvelle tâche
    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity body) {
        return this.taskService.createTask(body);
    }

    // Modifier une tâche grâce à son id
    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity body) {
        return this.taskService.updateTask(id, body);
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }
}
