package com.backempresa.Trip.Application;

import com.backempresa.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripCensoredOutputDto;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TripService {
    List<TripOutputDto> getAllTrip();
    TripOutputDto filterTripById(UUID id);
    List<TripOutputDto> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
    List<TripCensoredOutputDto> findByDepartureAndArrivalAndLocalDate(String departure, String arrival, String date);
    TripOutputDto addTrip(TripInputDto tripInputDto);
    TripOutputDto updateTrip(UUID id, TripInputDto tripInputDto);
    void deleteTrip(UUID id);
}
