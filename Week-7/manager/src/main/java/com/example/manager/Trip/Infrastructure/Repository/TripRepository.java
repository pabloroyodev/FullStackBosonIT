package com.example.manager.Trip.Infrastructure.Repository;

import com.example.manager.Trip.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
}
