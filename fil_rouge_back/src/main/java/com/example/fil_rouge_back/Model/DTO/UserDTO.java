package com.example.fil_rouge_back.Model.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;


    private String username;
    private String password;
    private String email;
    private Set<Long> projectId;
    // Ajoutez une méthode pour définir le token JWT
    @Setter
    private String token; // Ajoutez un champ pour stocker le token JWT

    public void setToken(String token) {
        this.token = token;
        System.out.println("UserDTO:" + token);
    }
}
