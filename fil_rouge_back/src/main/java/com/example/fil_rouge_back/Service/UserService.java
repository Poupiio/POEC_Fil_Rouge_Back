package com.example.fil_rouge_back.Service;

import com.example.fil_rouge_back.Model.DTO.LoginDTO;
import com.example.fil_rouge_back.Model.DTO.ProjectDTO;
import com.example.fil_rouge_back.Model.DTO.UserDTO;
import com.example.fil_rouge_back.Model.Entity.User;
import com.example.fil_rouge_back.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private  UserRepository userRepo;
    private JwtService jwtService;

    private ProjectService projectService;

    @Autowired
    public UserService(UserRepository userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
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
        User user = convertToUser(userDTO);
        User savedUser = this.userRepo.save(user);

        // Générer un token JWT pour le nouvel utilisateur
        String token = jwtService.generateToken(savedUser.getEmail());
        userDTO.setToken(token);
        System.out.println("UserService create User:" + token);


        return convertToUserDTO(savedUser);
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


    // Modifiez votre service utilisateur
    public LoginDTO login(LoginDTO loginDTO) throws AuthenticationException {
        Optional<User> userOptional = userRepo.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = jwtService.generateToken(user.getEmail());
            loginDTO.setToken(token);
            return loginDTO;
        } else {
            throw new AuthenticationException("Invalid email or password");
        }
    }

    public LoginDTO convertToLoginDTO(User user){
        LoginDTO loginDTO =  new LoginDTO();
        loginDTO.setEmail(user.getEmail());
        loginDTO.setPassword(user.getPassword());
        return loginDTO;
    }

    public UserDTO register(UserDTO userDTO){
        return this.createUser(userDTO);
    }
}