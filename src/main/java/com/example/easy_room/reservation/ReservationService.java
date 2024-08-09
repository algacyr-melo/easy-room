package com.example.easy_room.reservation;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation bookRoom(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
