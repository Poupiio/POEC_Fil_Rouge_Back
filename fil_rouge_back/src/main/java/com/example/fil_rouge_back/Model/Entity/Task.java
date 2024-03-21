package com.example.fil_rouge_back.Model.Entity;

import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Task {

    // Constructeur sans paramètre
    public Task(){}

    // Constructeur avec tous les paramètres
    public Task(Long id, String title, String description, Status status, Float estimationHours/*, Project projectId, Optional<User> userId*/) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.estimationHours = estimationHours;
        /* this.projectId = projectId;
        this.userId = userId;
        */
    }


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)


    private Long id;
    private String title;
    private String description;
    private Status status;
    private Float estimationHours;
    /*
    private Project projectId;
    private Optional<User> userId;
    */
}
