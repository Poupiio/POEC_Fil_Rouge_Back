package com.example.fil_rouge_back.Model.Entity;

import jakarta.persistence.*;

@Entity
public class Task {

    // Constructeur sans paramètre
    public Task(){}

    // Constructeur avec tous les paramètres
    public Task(Long id, String title, String description, Status status, Float estimationHours, Project projectId/*, Optional<User> userId*/) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.estimationHours = estimationHours;
        this.projectId = projectId;
       // this.userId = userId;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)


    private Long id;
    private String title;
    private String description;
    private Status status;
    private Float estimationHours;
@ManyToOne
@JoinColumn
    private Project projectId;
    //private Optional<User> userId;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getEstimationHours() {
        return estimationHours;
    }

    public void setEstimationHours(Float estimationHours) {
        this.estimationHours = estimationHours;
    }


    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
      /*

    public Optional<User> getUserId() {
        return userId;
    }

    public void setUserId(Optional<User> userId) {
        this.userId = userId;
    }
    */
}
