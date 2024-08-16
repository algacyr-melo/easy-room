package com.example.easy_room.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.reservation.exceptions.ReservationDateNotAvailableException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final HotelRoomService hotelRoomService;

    public Reservation createReservation(ReservationCreationDTO reservationCreationDTO) {
        if (!isReservationDateAvailable(reservationCreationDTO)) {
            throw new ReservationDateNotAvailableException(
                reservationCreationDTO.hotelRoomId(),
                reservationCreationDTO.checkInDate(),
                reservationCreationDTO.checkOutDate()
            );
        }

        HotelRoom hotelRoom = hotelRoomService
            .findHotelRoomById(reservationCreationDTO.hotelRoomId());

        Reservation reservation = new Reservation(reservationCreationDTO);
        reservation.setHotelRoom(hotelRoom);
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
