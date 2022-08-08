package com.example.gymtracker.controllers;

import com.example.gymtracker.models.User;
import com.example.gymtracker.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/create-user")
    public String createUserForm (Model model) {
        model.addAttribute("user", new User());
        return "views/user/create-user";
    }

    @PostMapping("/create-user")
    public String createdUser(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String user1 = user.getUserName();

        Long id = user.getId();

        model.addAttribute("id", id);
        model.addAttribute("user", user1);

        if(!userDao.existsById(id)){
            return "views/home";
        }


        return "views/user/user-profile";
    }

    @GetMapping("/login")
    public String login(){
        return "views/user/login";
    }


//    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api")
    public String api(){
        return "views/api-test";
    }


}
