package com.backweb.Trip.Application;

import com.backweb.Trip.Domain.Trip;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripCensoredOutputDto;
import com.backweb.Trip.Infrastructure.Repository.TripRepository;
import com.backweb.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.backweb.Utils.Kafka.Producer.KafkaSender;
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
    public List<TripCensoredOutputDto> findByDepartureAndArrivalAndLocalDate(String departure, String arrival, String date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndLocalDate(departure, arrival, date);
        return trips.stream().map(TripCensoredOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = tripInputDtoToEntity(tripInputDto);
        if (tripInputDto.isIssue()) {trip.setSeats(0);}

        trip.setIdTrip(UUID.randomUUID());
        tripRepository.save(trip);

        TripOutputDto tripOutputDto = new TripOutputDto(trip);

        sender.sendMessage(topic, tripOutputDto, port, "create", "trip");

        return tripOutputDto;
    }

    @Override
    public TripOutputDto updateTrip(UUID id, TripInputDto tripInputDto) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        if (tripInputDto.isIssue()) {trip.setSeats(0);}

        if (!tripInputDto.isIssue()) {
            if (trip.isIssue()) {
                trip.setSeats(40);
            }
        }

        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        tripRepository.save(trip);
        TripOutputDto tripOutputDto = EntityToTripOutDto(trip);
        sender.sendMessage(topic, tripOutputDto, port, "update", "trip");

        return new TripOutputDto(trip);
    }

    @Override
    public void deleteTrip(UUID id) {
        TripOutputDto tripOutputDto = EntityToTripOutDto(tripRepository.findById(id).orElseThrow());
        tripRepository.delete(tripRepository.findById(id).orElseThrow());
        sender.sendMessage(topic, tripOutputDto, port, "delete", "trip");
    }

    public Trip tripInputDtoToEntity(TripInputDto tripInputDto){
        Trip trip = new Trip();
        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        return trip;
    }

    public Trip tripOutDtoToEntity(TripOutputDto tripOutputDto){
        Trip trip = new Trip();
        trip.setIdTrip(tripOutputDto.getIdTrip());
        trip.setDate(tripOutputDto.getDate());
        trip.setDeparture(tripOutputDto.getDeparture());
        trip.setArrival(tripOutputDto.getArrival());
        trip.setSeats(tripOutputDto.getSeats());
        trip.setDeniedSeats(tripOutputDto.getDeniedSeats());
        trip.setIssue(tripOutputDto.isIssue());

        return trip;
    }

    public TripOutputDto EntityToTripOutDto(Trip trip) {
        TripOutputDto tripOutputDto = new TripOutputDto();
        tripOutputDto.setIdTrip(trip.getIdTrip());
        tripOutputDto.setDate(trip.getDate());
        tripOutputDto.setDeparture(trip.getDeparture());
        tripOutputDto.setArrival(trip.getArrival());
        tripOutputDto.setSeats(trip.getSeats());
        tripOutputDto.setDeniedSeats(trip.getDeniedSeats());
        tripOutputDto.setIssue(trip.isIssue());

        return tripOutputDto;
    }
}
