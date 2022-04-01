package com.example.manager.Trip.Application;

import com.example.manager.Trip.Domain.Trip;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    TripRepository tripRepository;

    @Override
    public List<TripOutputDto> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDto filterTripById(Integer id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        return new TripOutputDto(trip);
    }

    //Si alguien desea comprar un ticket, sin saber el tripId, deberemos buscar por estos campos.
    @Override
    public TripOutputDto filterTripByDepartureAndArrivalAndDate(String departure, String arrival, Date date) {
        Trip trip = tripRepository.filterTripByDepartureAndArrivalAndDate(departure,arrival,date);
        return new TripOutputDto(trip);
    }

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = tripInputDtoToEntity(tripInputDto);
        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public void deleteTrip(Integer id) {
        tripRepository.delete(tripRepository.findById(id).orElseThrow());
    }

    public Trip tripInputDtoToEntity(TripInputDto tripInputDto){
        Trip trip = new Trip();
        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        return trip;
    }
}
