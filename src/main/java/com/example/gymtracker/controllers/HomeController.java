package com.example.gymtracker.controllers;

import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    private final WorkoutRepository workoutDao;

    public HomeController(WorkoutRepository workoutDao) {
        this.workoutDao = workoutDao;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Workout> workoutList = workoutDao.findAll();

        Collections.reverse(workoutList);

        model.addAttribute("workout", workoutList);

        return "views/home";
    }

}
