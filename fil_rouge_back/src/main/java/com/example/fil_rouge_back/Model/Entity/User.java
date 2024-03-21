package com.example.fil_rouge_back.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long Id;
    private String username;
    private String password;
    private  String email;

    @OneToMany(mappedBy = "userId",fetch = FetchType.LAZY)
    private Set<Project> project = new HashSet<>();
}
