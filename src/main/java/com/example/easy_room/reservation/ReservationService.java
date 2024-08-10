package com.example.easy_room.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public boolean isReservationDateAvailable(ReservationCreationDTO reservationCreationDTO) {

        List<Reservation> reservationsOverlap = reservationRepository
            .findReservationOverlap(
                reservationCreationDTO.hotelRoomId(),
                reservationCreationDTO.checkInDate(),
                reservationCreationDTO.checkOutDate()
        );
        return reservationsOverlap.isEmpty();
    }
}
