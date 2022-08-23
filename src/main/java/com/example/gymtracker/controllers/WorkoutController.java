package com.example.gymtracker.controllers;


import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.UserRepository;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class WorkoutController {
    private Workout workout;

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;

    public WorkoutController(WorkoutRepository workoutDao, UserRepository userDao) {

        this.workoutDao = workoutDao;
        this.userDao = userDao;
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

        return "redirect:/";
    }

    @GetMapping("/exercise/{id}")
    public String showExercise(Model model, @PathVariable long id){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Workout workout = workoutDao.getById(id);

//        if(user.getId() != 0){
//            User user1 = userDao.getById(user.getId());
//            model.addAttribute("user", user1);
//        }

        model.addAttribute("workout", workout);

        return "views/exercises/individual-workout";

    }

    @GetMapping("/strength")
    public String showStrength(Model model){

        List<Workout> workouts = workoutDao.findAll();

        System.out.println(workouts);

        model.addAttribute("workouts", workouts);
        return "views/exercises/exercise-by-strength";
    }

    @GetMapping("/cardio")
    public String showCardio(Model model){

        List<Workout> workouts = workoutDao.findAll();

        System.out.println(workouts);

        model.addAttribute("workouts", workouts);
        return "views/exercises/exercise-by-cardio";
    }

    @GetMapping("/weigh-loss")
    public String showLoss(Model model){

        List<Workout> workouts = workoutDao.findAll();

        System.out.println(workouts);

        model.addAttribute("workouts", workouts);
        return "views/exercises/weight-loss";
    }

    @GetMapping("/endurance")
    public String showEndurance(Model model){

        List<Workout> workouts = workoutDao.findAll();

        System.out.println(workouts);

        model.addAttribute("workouts", workouts);
        return "views/exercises/endurance";
    }

    @GetMapping("/search")
    public String searchWorkouts(Model model, @RequestParam(name = "search") String search){

        List<Workout> workoutList = workoutDao.findByNameContainsIgnoreCase(search);

        System.out.println(workoutList);


        model.addAttribute("workout", workoutList);

        return "views/exercises/search";
    }

    @GetMapping("/workout-search")
    public String searched(Model model, @RequestParam(name = "search") String search){

        List<Workout> workoutList = workoutDao.findByNameContainsIgnoreCase(search);

        System.out.println(workoutList);


        model.addAttribute("workout", workoutList);

        return "redirect: /search";

    }




}
