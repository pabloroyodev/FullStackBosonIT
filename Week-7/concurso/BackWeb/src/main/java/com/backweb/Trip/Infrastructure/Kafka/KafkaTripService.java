package com.backweb.Trip.Infrastructure.Kafka;

import com.backweb.Trip.Application.TripServiceImpl;
import com.backweb.Trip.Domain.Trip;
import com.backweb.Trip.Infrastructure.Repository.TripRepository;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTripService {
    @Autowired
    TripServiceImpl tripService;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TripOutputDto tripOutputDto) {
        switch (action) {
            case "create" -> {
                Trip trip = tripService.tripOutDtoToEntity(tripOutputDto);
                System.out.println(trip);
                tripRepository.save(trip);
                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                Trip trip = tripRepository.findById(tripOutputDto.getIdTrip()).orElseThrow();

                trip.setDate(tripOutputDto.getDate());
                trip.setDeparture(tripOutputDto.getDeparture());
                trip.setArrival(tripOutputDto.getArrival());
                trip.setIssue(tripOutputDto.isIssue());

                tripRepository.save(trip);

                System.out.println("UPDATE SUCCESS");
            }

            case "delete" -> {
                tripRepository.delete(tripRepository.findById(tripOutputDto.getIdTrip()).orElseThrow());
                System.out.println("DELETE SUCCESS");
            }
        }
    }
}
