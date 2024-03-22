package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.DTO.TaskDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Service.ProjectService;
import com.example.fil_rouge_back.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/{projectId}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;


    // Récupérer toutes les tâches
    @GetMapping
    public List<TaskDTO> getAllTasks(@PathVariable Long projectId) {
        // Récupération de l'id du projet
        this.projectService.getProjectById(projectId).get();

        return this.taskService.findAllTasksByProjectId(projectId);
    }

    // Récupérer une tâche grâce à son id
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return this.taskService.getTaskById(id);
    }

    // Récupérer une tâche grâce à son nom
    @GetMapping("/title/{title}")
    public TaskDTO findByTitle(@PathVariable String title) {
        return taskService.findByTitle(title);
    }

    // Créer une nouvelle tâche
    @PostMapping("/create")
    public TaskDTO createTask(@PathVariable Long projectId, @RequestBody TaskDTO task) {
        // Récupération de l'id du projet
        //this.projectService.getProjectById(projectId).get();
        //System.out.println("Côté controller : " + projectId);
        Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        Long projectID = project.getId();
        System.out.println("ID du projet côté controller : " + projectID);

        return this.taskService.createTask(projectID, task);
    }

    // Modifier une tâche grâce à son id
    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO body) {
        return this.taskService.updateTask(id, body);
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }
}
