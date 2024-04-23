package com.example.fil_rouge_back.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    // Méthode pour générer un token JWT
    public String generateToken(Long userId, String userEmail) {
        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        // Utiliser la clé secrète générée pour signer le token JWT
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);

        // Durée de validité du token (1 heure dans cet exemple)
        long expirationTimeMillis = 3600000; // 1 heure en millisecondes
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeMillis);

        // Génération du token JWT
        String token = Jwts.builder()
                .claim("id", userId)
                .setSubject(userEmail) // Sujet du token (dans ce cas, l'email de l'utilisateur)
                .setIssuedAt(now) // Date de création du token
                .setExpiration(expirationDate) // Date d'expiration du token
                .signWith(SignatureAlgorithm.HS256, secretKey) // Signature du token avec la clé secrète
                .compact();
        System.out.println("JWT service:" + token);
        return token;
    }

    // Autres méthodes du service UserService...
}