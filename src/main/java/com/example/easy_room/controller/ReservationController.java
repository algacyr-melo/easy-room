package com.example.easy_room.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.reservation.Reservation;
import com.example.easy_room.reservation.ReservationCreationDTO;
import com.example.easy_room.reservation.ReservationService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Transactional
    @PostMapping
    public ResponseEntity<ReservationCreationDTO> createReservation(
        @Valid @RequestBody ReservationCreationDTO reservationCreationDTO,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        Reservation reservation = reservationService.createReservation(reservationCreationDTO);

        URI location = uriComponentsBuilder
            .path("/reservation/{id}")
            .buildAndExpand(reservation.getId())
            .toUri();

        return ResponseEntity.created(location).body(reservationCreationDTO);
    }
}
