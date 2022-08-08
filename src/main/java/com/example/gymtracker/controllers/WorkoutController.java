package com.example.gymtracker.controllers;


import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class WorkoutController {
    private Workout workout;

    private final WorkoutRepository workoutDao;

    public WorkoutController(WorkoutRepository workoutDao) {
        this.workoutDao = workoutDao;
    }


    @GetMapping("/workout-create")
    public String workouts(Model model) {
        model.addAttribute("exercise", new Workout());
        return "views/exercises/exercise-plan";
    }

    @PostMapping("/workout-create")
    public String workoutPost(Model model, @ModelAttribute Workout workout){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("exercise", workout);
        workout.setUser(user);

        workoutDao.save(workout);

        return "redirect:";
    }


}
