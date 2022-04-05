package com.example.manager.Trip.Application;

import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TripService {
    List<TripOutputDto> getAllTrip();
    TripOutputDto filterTripById(UUID id);
    List<TripOutputDto> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
    TripOutputDto addTrip(TripInputDto tripInputDto);
    TripOutputDto updateTrip(UUID id, TripInputDto tripInputDto);
    void deleteTrip(UUID id);
}