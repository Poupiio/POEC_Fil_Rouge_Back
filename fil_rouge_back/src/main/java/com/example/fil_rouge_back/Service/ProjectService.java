package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.DTO.UserDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.ProjectRepository;
import com.example.fil_rouge_back.Model.Repository.UserRepository;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.Set;


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
    public List<Project> getAllProjects() {
        return this.projectRepo.findAll();
    }

    // Récupération d'un projet grâce à son id
    public Set<Project> getProjectById(Long id) {
        Optional<Project> optionalProject = projectRepo.findById(id);
        return optionalProject.map(Collections::singleton).orElse(Collections.emptySet());
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
