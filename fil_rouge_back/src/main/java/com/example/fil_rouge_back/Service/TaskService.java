package com.example.fil_rouge_back.Service;


import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.DTO.TaskDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import com.example.fil_rouge_back.Model.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    public TaskRepository repo;

    @Autowired
    public ProjectService projectService;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.repo = taskRepository;
    }

    // Récupération de toutes les tâches
    public List<TaskDTO> findAllTasksByProjectId(Long projectId) {
        // Je récupère les tâches associées à un projet sous forme de liste, en récupérant l'id du projet
        List<TaskEntity> tasks = repo.findAllTasksByProjectId(projectId);
        /* Je retoure ensuite le résultat que je transforme en flux de données (stream),
            sur lequel je souhaite transformer chaque résultat en TaskDTO,
            puis j'utilise la méthode collect() pour stocker ce flux que je transforme en liste
         */
        return tasks.stream()
                .map(task -> convertToTaskDTO(task))
                .collect(Collectors.toList());
    }

    // Récupération d'une tache grâce à son id
    public TaskDTO findTaskByProjectIdAndId(Long projectId, Long taskId) {
        Optional<TaskEntity> taskOptional = repo.findTaskByProjectIdAndId(projectId, taskId);
        if (taskOptional.isPresent()) {
            return convertToTaskDTO(taskOptional.get());
        } else {
            throw new IllegalArgumentException("La tâche recherchée n'existe pas");
        }
    }

    // Récupérer une tâche grâce à son nom
    public TaskDTO findByTitle(String title) {
        Optional<TaskEntity> optionalTask = this.repo.findByTitle(title);
        if (optionalTask.isPresent()) {
            return convertToTaskDTO(optionalTask.get());
        } else {
            return null;
        }
    }

    // Création d'une tâche
    public TaskDTO createTask(Long projectId, TaskDTO taskDto) {
        // Je crée une nouvelle tâche de type TaskEntity en lui passant en paramètre la taskDto
        TaskEntity taskEntity = convertToTask(taskDto, projectId);

        // Je sauvegarde d'abord la nouvelle tâche
        TaskEntity savedTask = repo.save(taskEntity);

        // Pour la convertir en TaskDTO
        return convertToTaskDTO(savedTask);
    }

    // Modification d'une tâche
    public TaskDTO updateTask(Long id, TaskDTO data) {
        TaskEntity task = this.repo.findById(id).get();

        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setStatus(data.getStatus());
        task.setEstimationHours(data.getEstimationHours());

        return convertToTaskDTO(this.repo.save(task));
    }

    // Suppression d'une tâche
    public void deleteTask(Long id) {
        this.repo.deleteById(id);
    }

    // Conversion d'une TaskEntity en TaskDTO
    public TaskDTO convertToTaskDTO(TaskEntity task) {
        // Je crée un Task de type DTO
        TaskDTO taskDTO = new TaskDTO();

        // J'attribue les valeurs aux propriétés de TaskDTO
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setEstimationHours(task.getEstimationHours());
        taskDTO.setProjectId(task.getProject().getId());

        return taskDTO;
    }

    // Conversion d'un TaskDTO en TaskEntity
    public TaskEntity convertToTask(TaskDTO taskDTO, Long projectId) {
        // J'instancie un objet de type Task auquel j'attribue les valeurs des propriétés de TaskDTO
        TaskEntity task = new TaskEntity();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setEstimationHours(taskDTO.getEstimationHours());
        // Ici je fais appel au projectService me donnant accès à getNormalProjectById() pour obtenir l'id du projet de type Projet
        task.setProject(projectService.getNormalProjectById(projectId));

        return task;
    }

}