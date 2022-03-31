package com.example.manager.Trip.Infrastructure.Repository;

import com.example.manager.Trip.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
