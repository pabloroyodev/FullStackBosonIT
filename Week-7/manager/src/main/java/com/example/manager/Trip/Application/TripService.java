package com.example.manager.Trip.Application;

import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;

import java.util.Date;
import java.util.List;

public interface TripService {
    List<TripOutputDto> getAllTrip();
    TripOutputDto filterTripById(Integer id);
    TripOutputDto filterTripByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
    TripOutputDto addTrip(TripInputDto tripInputDto);
    TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto);
    void deleteTrip(Integer id);
}
