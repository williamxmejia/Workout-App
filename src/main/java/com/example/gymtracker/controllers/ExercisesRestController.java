package com.example.gymtracker.controllers;


import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.UserRepository;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExercisesRestController {

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;

    public ExercisesRestController(WorkoutRepository workoutDao, UserRepository userDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
    }

    @GetMapping("/exercises")
    List<Workout> allExercises(){
        return workoutDao.findAll();
    }

    @GetMapping("/users")
    List<User> allUsers(){
        return userDao.findAll();
    }

}
