package com.example.gymtracker.repositories;

import com.example.gymtracker.models.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingRepository extends PagingAndSortingRepository<Workout, Long> {
    Page<Workout> findAllBy(Pageable pageable);
}
