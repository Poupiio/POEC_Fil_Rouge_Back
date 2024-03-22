package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.UserDTO;
import com.example.fil_rouge_back.Model.Entity.Project;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private  UserRepository userRepo;

    private ProjectService projectService;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private void setProjectService (ProjectService projectService)
    {
        this.projectService = projectService;
    }


    // Récupération de tous les utilisateurs
    public List<UserDTO> getAllUsers() {

        return this.userRepo.findAll().stream().map(this::convertToUserDTO).toList();
    }


    public User getUserById(Long id) {
        return this.userRepo.findById(id).get();
    }
    // Récupération d'un utilisateur grâce à son id
    public UserDTO getUserDTOById(Long id) {
        return convertToUserDTO(getUserById(id));
    }




    // Création d'un utilisateur
    public UserDTO createUser(UserDTO data) {
        return convertToUserDTO(this.userRepo.save(convertToUser(data)));
    }

    // Modification d'un utilisateur
    public UserDTO updateUser(Long id, UserDTO data) {
        User user = this.userRepo.findById(id).get();
        user.setUsername(convertToUser(data).getUsername());
        user.setEmail(convertToUser(data).getEmail());
        user.setPassword(convertToUser(data).getPassword());
        return convertToUserDTO(this.userRepo.save(user));
    }

    // Suppression d'un utilisateur
    public void deleteUserDTO(Long id) {
        this.userRepo.deleteById(id);
    }

    //login
    public boolean login(UserDTO userdto) {
        return this.userRepo.findByEmailAndPassword(userdto.getEmail(), userdto.getPassword()).isPresent();
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
        user.setProject((Set<Project>) projectService.getProjectById(userdto.getProjectId()).orElse(null));

        return user;
    }
}
