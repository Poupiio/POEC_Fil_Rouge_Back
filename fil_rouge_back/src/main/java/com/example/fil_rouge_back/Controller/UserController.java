package com.example.fil_rouge_back.Controller;

import com.example.fil_rouge_back.Model.DTO.LoginDTO;
import com.example.fil_rouge_back.Model.DTO.UserDTO;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return this.userService.getUserDTOById(id);
    }
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO data) {
        return this.userService.createUser(data);
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO data) {
        return this.userService.updateUser(id, data);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
       this.userService.deleteUser(id);
    }


    @PostMapping("/login")
    public LoginDTO login(@RequestBody LoginDTO loginDTO) {
        return this.userService.login(loginDTO);

    }
    @PostMapping("/register")
    public UserDTO Register(@RequestBody UserDTO userDTO){
        return this.userService.register(userDTO);
    }
    
}
