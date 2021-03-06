package com.backempresa.Trip.Application;

import com.backempresa.Mail.Domain.Mail;
import com.backempresa.Mail.Infrastructure.Repository.MailRepository;
import com.backempresa.Trip.Domain.Trip;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripCensoredOutputDto;
import com.backempresa.Trip.Infrastructure.Repository.TripRepository;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.backempresa.Utils.Kafka.Producer.KafkaSender;
import com.backempresa.Utils.Mail.MailSenderService;
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
    MailRepository mailRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    KafkaSender sender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    String TRIP = "trip";

    @Override
    public List<TripOutputDto> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDto::new).toList();
    }

    @Override
    public TripOutputDto filterTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        return new TripOutputDto(trip);
    }

    @Override
    public List<TripOutputDto> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndDate(departure,arrival, date);
        return trips.stream().map(TripOutputDto::new).toList();
    }

    @Override
    public List<TripCensoredOutputDto> findByDepartureAndArrivalAndLocalDate(String departure, String arrival, String date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndLocalDate(departure, arrival, date);
        return trips.stream().map(TripCensoredOutputDto::new).toList();
    }

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = tripInputDtoToEntity(tripInputDto);
        if (tripInputDto.isIssue()) {trip.setSeats(0);}

        trip.setIdTrip(UUID.randomUUID());
        tripRepository.save(trip);

        TripOutputDto tripOutputDto = new TripOutputDto(trip);

        sender.sendMessage(topic, tripOutputDto, port, "create", TRIP);

        return tripOutputDto;
    }

    @Override
    public TripOutputDto updateTrip(UUID id, TripInputDto tripInputDto) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        if (tripInputDto.isIssue()) {
            trip.setSeats(0);
                for (int i = 0; i < trip.getTickets().size(); i++) {
                    mailSenderService.sendMail(
                          trip.getTickets().get(i).getClient().getEmail(),
                          "Aviso Importante. Su viaje ha sido cancelado",
                          "Le informamos que su viaje desde " + trip.getDeparture() + " a " + trip.getArrival() + " para la fecha " + trip.getDate()
                                  + " ha sido cancelado de manera forzosa por huelga, averia o otro motivo excepcional.\n"
                                  + "El identificador del ticket cancelado es: " + trip.getTickets().get(i).getIdTicket());

                    Mail mail = new Mail(UUID.randomUUID(), trip.getDate(), trip.getDeparture(), trip.getArrival(), trip.getTickets().get(i).getClient().getEmail(), "CANCELACION FORZOSA");
                    mailRepository.save(mail);
                    sender.sendMessage(topic, mail, port, "create", "mail");
                }
        }

        if (!tripInputDto.isIssue() && trip.isIssue()) {
                trip.setSeats(40);
        }

        trip.setDate(tripInputDto.getDate());
        trip.setDeparture(tripInputDto.getDeparture());
        trip.setArrival(tripInputDto.getArrival());
        trip.setIssue(tripInputDto.isIssue());

        tripRepository.save(trip);
        TripOutputDto tripOutputDto = EntityToTripOutDto(trip);
        sender.sendMessage(topic, tripOutputDto, port, "update", TRIP);

        return new TripOutputDto(trip);
    }

    @Override
    public void deleteTrip(UUID id) {
        TripOutputDto tripOutputDto = EntityToTripOutDto(tripRepository.findById(id).orElseThrow());
        tripRepository.delete(tripRepository.findById(id).orElseThrow());
        sender.sendMessage(topic, tripOutputDto, port, "delete", TRIP);
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
