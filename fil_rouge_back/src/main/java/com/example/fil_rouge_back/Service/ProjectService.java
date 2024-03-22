package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectService {
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Autowired
    private ProjectRepository projectRepo;



    // Récupération de tous les projets
    /*public List<ProjectDTO> getAllProjects(Long userId) {
        return this.projectRepo.findAll().stream().map(this::convertToProjectDTO).toList();
    }

     */
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
        /*Optional<Project> projectOptional = this.projectRepo.findById(id);
        if (projectOptional.isPresent()) {
            ProjectDTO projectDTO = convertToProjectDTO(projectOptional.get());
            return Optional.of(projectDTO);
        } else {
            return Optional.empty();
        }
         */
        return this.convertToProjectDTO(this.projectRepo.getProjectById(id));
    }

    public Project getProjecNormaltById(Long id) {
        /*Optional<Project> projectOptional = this.projectRepo.findById(id);
        if (projectOptional.isPresent()) {
            ProjectDTO projectDTO = convertToProjectDTO(projectOptional.get());
            return Optional.of(projectDTO);
        } else {
            return Optional.empty();
        }
         */
        return this.projectRepo.getProjectById(id);
    }


    // Création d'un projet
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        return convertToProjectDTO(this.projectRepo.save(convertToProject(projectDTO)));
    }

    // Modification d'un projet
    public ProjectDTO updateProject(Long id, ProjectDTO data) {
        Project project = this.projectRepo.findById(id).get();
        project.setName(data.getName());
        project.setUser(this.userService.getUserById(id));

        return convertToProjectDTO(projectRepo.save(project));
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
        project.setName(projectDTO.getName());
        project.setUser(userService.getUserById(projectDTO.getUserId()));
        project.setId(projectDTO.getId());


        return project;
    }

}
