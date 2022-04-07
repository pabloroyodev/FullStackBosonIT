package com.example.manager.Trip.Application;

import com.example.manager.Trip.Domain.Trip;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
import com.example.manager.Utils.Kafka.Producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    TripRepository tripRepository;

    @Autowired
    KafkaSender sender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    @Override
    public List<TripOutputDto> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDto filterTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        return new TripOutputDto(trip);
    }

    //Si alguien desea comprar un ticket, sin saber el tripId, deberemos buscar por estos campos.
    @Override
    public List<TripOutputDto> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndDate(departure,arrival, date);
        return trips.stream().map(TripOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = tripInputDtoToEntity(tripInputDto);
        tripRepository.save(trip);

        TripOutputDto tripOutputDto = new TripOutputDto(trip);
        sender.sendMessage(topic, tripOutputDto, port, "create", "trip");

        return tripOutputDto;
    }

    @Override
    public TripOutputDto updateTrip(UUID id, TripInputDto tripInputDto) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public void deleteTrip(UUID id) {
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
