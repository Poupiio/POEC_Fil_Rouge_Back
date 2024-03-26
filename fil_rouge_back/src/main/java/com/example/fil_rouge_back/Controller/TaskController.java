package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.DTO.TaskDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Service.ProjectService;
import com.example.fil_rouge_back.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
        this.projectService.getProjectById(projectId);

        return this.taskService.findAllTasksByProjectId(projectId);
    }

    // Récupérer une tâche grâce à son id
    // ATTENTION : le nom du paramètre dans la route doit être LE MÊME que le paramètre de requête : {taskId} = Long taskId)
    @GetMapping("/{taskId}")
    public TaskDTO getTaskByProjectIdAndId(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskService.findTaskByProjectIdAndId(projectId, taskId);
    }

    // Récupérer une tâche grâce à son nom
    @GetMapping("/title/{encodedTitle}")
    public TaskDTO findByTitle(@PathVariable("encodedTitle") String encodedTitle) {
        // J'utilise un décodeur d'URL afin de pouvoir rechercher un titre qui contient des espaces
        // (A tester sur Postman en ajoutant "%20" après chaque espace)
        String decodedTitle = URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8);

        return taskService.findByTitle(decodedTitle);
    }

    // Créer une nouvelle tâche
    @PostMapping("/create")
    public TaskDTO createTask(@PathVariable Long projectId, @RequestBody TaskDTO task) {
        // Je vérifie si le projet existe
        ProjectDTO project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new RuntimeException("Projet non trouvé");
        }

        // Si le projet est bien trouvé, je crée la tâche avec l'id du projet
        return this.taskService.createTask(projectId, task);
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
