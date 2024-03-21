package com.example.fil_rouge_back.Model.Entity;

import jakarta.persistence.*;
import org.springframework.scheduling.config.Task;



@Entity
public class Project() {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;



    private Long user_id;
    @OneToMany(mappedBy = "project")
    private <Set>Task tasks;



    public Project(Long id, String name, Long user_id, Task task) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.task = task;
    }

    public Project() {
    }



    public static Project createProject() {
        return new Project();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Task getTasks() {
        return tasks;
    }

    public void setTasks(Task tasks) {
        this.tasks = tasks;
    }
}
