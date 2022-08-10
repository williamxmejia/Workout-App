package com.example.gymtracker.repositories;

import com.example.gymtracker.models.User;
import com.example.gymtracker.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);

    List<Workout> findByTypeOfWorkout(String typeOfWorkout);










}
