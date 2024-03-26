package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
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
import java.util.stream.Collectors;

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

    // Récupération d'un utilisateur User grâce à son id
    public User getUserById(Long id) {
        return this.userRepo.findById(id).get();
    }

    // Récupération d'un utilisateur UserDTO grâce à son id
    public UserDTO getUserDTOById(Long id) {
        return convertToUserDTO(getUserById(id));
    }

    // Création d'un utilisateur
    public UserDTO createUser(UserDTO userDTO) {
        return convertToUserDTO(this.userRepo.save(convertToUser(userDTO)));
    }

    // Modification d'un utilisateur
    public UserDTO updateUser(Long id, UserDTO data) {
        User user = this.userRepo.findById(id).get();
        System.out.println("id du user à modifier : " + id);

        user.setUsername(convertToUser(data).getUsername());
        user.setEmail(convertToUser(data).getEmail());
        user.setPassword(convertToUser(data).getPassword());

        return convertToUserDTO(this.userRepo.save(user));
    }

    // Suppression d'un utilisateur
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }

    //login
    public boolean login(UserDTO userdto) {
        return this.userRepo.findByEmailAndPassword(userdto.getEmail(), userdto.getPassword()).isPresent();
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userdto = new UserDTO();

        if (user.getProject() == null) {
            throw new IllegalArgumentException("Aucun utilisateur n'est lié à ce projet");
        }

        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());

        // Je récupère le(s) projet(s) de l'utilisateur en faisant appel à la fonction qui récupère tous les projets d'un user
        List<ProjectDTO> projects = projectService.getAllProjects(user.getId());
        // Je récupère le flux que je convertis en liste
        Set<Long> projectIds = projects.stream().map(ProjectDTO::getId).collect(Collectors.toSet());
        userdto.setProjectId(projectIds);

        return userdto;
    }

    public User convertToUser(UserDTO userdto){
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());
        user.setEmail(userdto.getEmail());

        return user;
    }
}
