package com.example.fil_rouge_back.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
    @Setter
    private String token; // Ajoutez un champ pour stocker le token JWT

    // Ajoutez une méthode pour définir le token JWT
    public void setToken(String token) {
        this.token = token;
        System.out.println("LoginDTO:" + token);
    }
}
