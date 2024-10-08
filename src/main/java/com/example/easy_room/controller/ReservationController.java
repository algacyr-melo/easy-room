package com.example.easy_room.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easy_room.reservation.Reservation;
import com.example.easy_room.reservation.ReservationService;
import com.example.easy_room.reservation.dto.ReservationCreationDTO;
import com.example.easy_room.reservation.dto.ReservationReadDTO;
import com.example.easy_room.reservation.dto.ReservationUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/reservations")
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

    @GetMapping
    public ResponseEntity<Page<ReservationReadDTO>> getReservations(Pageable pageable) {
        Page<ReservationReadDTO> page = reservationService.getReservationsPage(pageable);
        return ResponseEntity.ok(page);
    }

    /* TODO: Return the Reservation entity from service and manage
     * it here instead of returning the DTO directly*/
    @GetMapping("/{id}")
    public ResponseEntity<ReservationReadDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ReservationReadDTO> updateReservation(
        @Valid @RequestBody ReservationUpdateDTO reservationUpdateDTO
    ) {
        Reservation reservation = reservationService.updateReservation(reservationUpdateDTO);

        return ResponseEntity.ok(new ReservationReadDTO(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
