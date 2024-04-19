package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;


@Service
public class ProjectService {
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;
    private TaskService taskService;

    @Autowired
    private ProjectRepository projectRepo;



    // Récupération de tous les projets

    public List<ProjectDTO> getAllProjects(Long userId) {
        // Récupérer l'utilisateur par son ID
        User user = userService.getUserById(userId);
        if (user == null) {
            // Gérer le cas où l'utilisateur n'existe pas (vous pouvez lancer une exception ou retourner une liste vide)
            return Collections.emptyList(); // ou lancez une exception appropriée
        }

        // Récupérer tous les projets associés à l'utilisateur
        List<Project> userProjects = projectRepo.findByUserId(userId);

        // Convertir les projets en DTO
        List<ProjectDTO> projectDTOs = userProjects.stream()
                .map(this::convertToProjectDTO)
                .collect(Collectors.toList());

        return projectDTOs;
    }

    // Récupération d'un projet grâce à son id
    public ProjectDTO getProjectById(Long id) {
        return this.convertToProjectDTO(this.projectRepo.findById(id).orElse(new Project()));
    }

    public Project getNormalProjectById(Long id) {
       // Récupération d'un projet de type Projet
        return this.projectRepo.findById(id).orElse(new Project());
    }


    // Création d'un projet
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        return convertToProjectDTO(this.projectRepo.save(convertToProject(projectDTO)));
    }

    // Modification d'un projet
    public ProjectDTO updateProject(Long id, ProjectDTO projectDto) {
        // Je récupère l'id du projet
        Project project = this.getNormalProjectById(id);

        // Je set seulement le name (car on ne modifie ni l'id ni l'id du user)
        project.setName(projectDto.getName());

        // Je le sauvegarde en base en le convertissant en ProjectDTO
        return convertToProjectDTO(this.projectRepo.save(project));
    }

    // Suppression d'un projet
    public void deleteProject(Long id) {
        this.projectRepo.deleteById(id);
    }

    public ProjectDTO convertToProjectDTO(Project project) {
        // S'il n'y a aucun user relié au projet, je return une exception
        if (project.getUser() == null) {
            throw new IllegalArgumentException("Aucun utilisateur n'est lié à ce projet");
        }

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setUserId(project.getUser().getId());
        return projectDTO;
    }

    public Project convertToProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setUser(userService.getUserById(projectDTO.getUserId()));

        return project;
    }

}
