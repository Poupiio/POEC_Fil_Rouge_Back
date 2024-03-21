package com.example.fil_rouge_back.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.config.Task;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;



    private String name;
    private Long userId;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY,cascade = CascadeType.ALL)

    private Set<TaskEntity> tasks = new HashSet<>();

    public Project(Long id, String name, Long userId,@Lazy Set<TaskEntity> tasks) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.tasks = tasks;
    }
}



