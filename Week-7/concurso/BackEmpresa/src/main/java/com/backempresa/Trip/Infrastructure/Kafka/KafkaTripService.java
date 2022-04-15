package com.backempresa.Trip.Infrastructure.Kafka;

import com.backempresa.Trip.Application.TripServiceImpl;
import com.backempresa.Trip.Domain.Trip;
import com.backempresa.Trip.Infrastructure.Repository.TripRepository;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaTripService {
    @Autowired
    TripServiceImpl tripService;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TripOutputDto tripOutputDto) {
        switch (action) {
            case "create" -> {
                Trip trip = tripService.tripOutDtoToEntity(tripOutputDto);
                tripRepository.save(trip);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                Trip trip = tripRepository.findById(tripOutputDto.getIdTrip()).orElseThrow();

                trip.setDate(tripOutputDto.getDate());
                trip.setDeparture(tripOutputDto.getDeparture());
                trip.setArrival(tripOutputDto.getArrival());
                trip.setIssue(tripOutputDto.isIssue());
                trip.setSeats(tripOutputDto.getSeats());

                tripRepository.save(trip);

                log.info("UPDATE SUCCESS");
            }

            case "delete" -> {
                tripRepository.delete(tripRepository.findById(tripOutputDto.getIdTrip()).orElseThrow());

                log.info("DELETE SUCCESS");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE TRIP! ACCION NO ESPECIFICADA (create, update o delete)");
            }
        }
    }
}
