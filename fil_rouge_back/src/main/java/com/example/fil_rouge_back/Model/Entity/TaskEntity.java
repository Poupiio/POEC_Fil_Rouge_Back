package com.example.fil_rouge_back.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class TaskEntity {

    // Constructeur sans paramètre
    public TaskEntity(){}

    // Constructeur avec tous les paramètres
    public TaskEntity(Long id, String title, String description, Status status, Float estimationHours, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.estimationHours = estimationHours;

        this.project = project;
       // this.userId = userId;


    }


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String title;
    private String description;
    private Status status;
    private Float estimationHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
