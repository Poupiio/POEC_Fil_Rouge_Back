package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class ProjectService {
    @Autowired
    private ProjectRepository projectRepo;

    // Récupération de tous les projets
    public List<Project> getAllProjects() {
        return this.projectRepo.findAll();
    }

    // Récupération d'un projet grâce à son id
    public Set<Project> getProjectById(Long id) {
        return this.projectRepo.findById(id);
    }

    //Récupérer un projet grâce à son nom
    public Optional<Project> findByName(String name) {
        return this.projectRepo.findByName(name);
    }

    // Création d'un projet
    public Project createProject(Project data) {
        return this.projectRepo.save(data);
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

}
