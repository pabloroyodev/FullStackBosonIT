package com.backempresa.Trip.Infrastructure.Repository;

import com.backempresa.Trip.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {

    List<Trip> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);

    @Query(value = "SELECT * FROM TRIP t " +
           "WHERE t.DEPARTURE = :departure AND t.ARRIVAL = :arrival AND t.DATE LIKE CONCAT(:date, '%')", nativeQuery = true)
    List<Trip> findByDepartureAndArrivalAndLocalDate(@Param("departure") String departure, @Param("arrival") String arrival, @Param("date") String date);
}
