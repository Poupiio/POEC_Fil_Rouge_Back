package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return this.projectService.getProjectById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<Project> getProjectByName(@PathVariable String name) {
        return this.projectService.findByName(name);
    }

    @PostMapping
    public Project createProject(@RequestBody Project data) {
        return this.projectService.createProject(data);
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

