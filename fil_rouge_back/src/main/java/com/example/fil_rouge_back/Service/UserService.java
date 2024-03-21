package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.UserDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    private ProjectService projectService;

    // Récupération de tous les utilisateurs
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    // Récupération d'un utilisateur grâce à son id
    public User getUserById(Long id) {
        return this.userRepo.findById(id).get();
    }


    // Création d'un utilisateur
    public User createUser(User data) {
        return this.userRepo.save(data);
    }

    // Modification d'un utilisateur
    public User updateUser(Long id, User data) {
        User user = this.userRepo.findById(id).get();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        return this.userRepo.save(user);
    }

    // Suppression d'un utilisateur
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }

    //login
    public boolean login(String email, String password) {
     List<User> users = this.userRepo.findAll();
     return users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));


    }

    public UserDTO convertToUserDTO(User user){
        UserDTO userdto = new UserDTO();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        if (user.getProject() != null && !user.getProject().isEmpty()) {
            Project project = user.getProject().iterator().next();
            userdto.setProjectId(project.getId());
        }

        return userdto;
    }


    public User convertToUser(UserDTO userdto){
        User user = new User();
        user.setId(userdto.getId());
        user.setUsername(userdto.getUsername());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setProject(projectService.getProjectById(userdto.getProjectId()));

        return user;
    }
}
