package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Service.ProjectService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return this.projectService.getAllProjects();

    }

    @GetMapping("/{id}")
    public Optional<ProjectDTO> getProjectById(@PathVariable Long id) {
        return this.projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return this.projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project data) {
        return this.projectService.updateProject(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        this.projectService.deleteProject(id);
    }
}

