package com.example.manager.Trip.Infrastructure.Controller.V0;

import com.example.manager.Trip.Application.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v0/trip")
@RestController
public class DeleteTrip {
    @Autowired
    TripService tripService;

    @DeleteMapping("{id}")
    public void deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
    }
}
