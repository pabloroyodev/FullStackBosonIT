package com.example.manager.Trip.Application;

import com.example.manager.Trip.Domain.Trip;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    TripRepository tripRepository;

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = tripInputDtoToEntity(tripInputDto);
        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    public Trip tripInputDtoToEntity(TripInputDto tripInputDto){
        Trip trip = new Trip();
        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setSeats(tripInputDto.getSeats());
        trip.setIssue(tripInputDto.isIssue());

        return trip;
    }
}
