package com.example.manager.Trip.Infrastructure.Repository;

import com.example.manager.Trip.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
