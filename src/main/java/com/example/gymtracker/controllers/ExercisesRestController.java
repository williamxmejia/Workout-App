package com.example.gymtracker.controllers;


import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.PagingRepository;
import com.example.gymtracker.repositories.UserRepository;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ExercisesRestController {

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;

    private final PagingRepository pagingDao;

    public ExercisesRestController(WorkoutRepository workoutDao, UserRepository userDao, PagingRepository pagingDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
        this.pagingDao = pagingDao;
    }

    @GetMapping("/exercises")
    List<Workout> allExercises() {
        return workoutDao.findAll();
    }

    @GetMapping("/users")
    List<User> allUsers() {
        return userDao.findAll();
    }


    @GetMapping("/pages/{id}")
    Page exercisePage(@PageableDefault(size = 10) Pageable pageable, @PathVariable String id) {
        return pagingDao.findAllBy(pageable.withPage(Integer.parseInt(id)));
    }



}
