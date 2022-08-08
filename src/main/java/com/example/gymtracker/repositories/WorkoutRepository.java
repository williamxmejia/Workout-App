package com.example.gymtracker.repositories;

import com.example.gymtracker.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {


}
