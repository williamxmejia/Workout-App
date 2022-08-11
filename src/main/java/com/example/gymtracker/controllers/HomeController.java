package com.example.gymtracker.controllers;

import com.example.gymtracker.models.Workout;
import com.example.gymtracker.repositories.PagingRepository;
import com.example.gymtracker.repositories.WorkoutRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    private final PagingRepository pagingDao;
    private final WorkoutRepository workoutDao;

    @Autowired
    public HomeController(PagingRepository pagingDao, WorkoutRepository workoutDao) {
        this.pagingDao = pagingDao;
        this.workoutDao = workoutDao;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Workout> workoutList = workoutDao.findAll();

        Page<Workout> name = pagingDao.findAll(PageRequest.of(0,5));

        System.out.println(name);


        Collections.reverse(workoutList);

        model.addAttribute("workout",name);

        return "views/home";
    }

}
