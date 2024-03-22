package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


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
    public List<ProjectDTO> getAllProjects() {
        return this.projectRepo.findAll().stream().map(this::convertToProjectDTO).toList();
    }

    // Récupération d'un projet grâce à son id
    public Optional<ProjectDTO> getProjectById(Long id) {

    return this.projectRepo.findById(id).map(this::convertToProjectDTO);
    }


    // Création d'un projet
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        return convertToProjectDTO(this.projectRepo.save(convertToProject(projectDTO)));
    }

    // Modification d'un projet
    public Project updateProject(Long id, Project data) {
        Project project = this.projectRepo.findById(id).get();
        project.setName(data.getName());

        return this.projectRepo.save(project);
    }

    // Suppression d'un projet
    public void deleteProject(Long id) {
        this.projectRepo.deleteById(id);
    }

    public ProjectDTO convertToProjectDTO(Project project) {
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
