package com.example.gymtracker.controllers;

import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.UserRepository;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    private final WorkoutRepository workoutDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, WorkoutRepository workoutDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.workoutDao = workoutDao;
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

        List<Workout> workout = workoutDao.findByUser(user);

        Long id = user.getId();

        model.addAttribute("id", id);
        model.addAttribute("user", user1);
        model.addAttribute("workout", workout);

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
