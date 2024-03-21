package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }
    @PostMapping
    public User createUser(@RequestBody User data) {
        return this.userService.createUser(data);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User data) {
        return this.userService.updateUser(id, data);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
       this.userService.deleteUser(id);
    }


    @GetMapping("/login")
    public Boolean login(@RequestParam String email, @RequestParam String password) {
        return this.userService.login(email, password);
    }



}
