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
import org.springframework.web.bind.annotation.PathVariable;

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
    public String home(Model model, @PageableDefault(value = 5, direction = Sort.Direction.ASC) Pageable pageable) {

        List<Workout> workoutList = workoutDao.findAll();

//        Page<Workout> name = pagingDao.findAll(PageRequest.of(0,5));

        Page<Workout> name = pagingDao.findAll(pageable);

        System.out.println(name);


        Collections.reverse(workoutList);

        model.addAttribute("workout",name);

        return "views/home";
    }


    @GetMapping("/home")
    public String getAllWorkouts(Model model, Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Workout> pagedResults = pagingDao.findAll(paging);

        model.addAttribute("workout", pagedResults);

        return "views/home";
    }


    @GetMapping("/{id}")
    public String exercisePageNext(Model model, @PageableDefault(size = 5) Pageable pageable, @PathVariable int id) {

        List<Workout> list = workoutDao.findAll();


        Page<Workout> pages = pagingDao.findAllBy(pageable.withPage(id));

        model.addAttribute("workout", pages);

        return "views/home";
    }

}
