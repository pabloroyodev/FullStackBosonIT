package com.example.manager.Trip.Infrastructure.Kafka;

import com.example.manager.Client.Application.ClientServiceImpl;
import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import com.example.manager.Trip.Application.TripService;
import com.example.manager.Trip.Application.TripServiceImpl;
import com.example.manager.Trip.Domain.Trip;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
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
