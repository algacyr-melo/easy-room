package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.reservation.Reservation;
import com.example.easy_room.reservation.ReservationRepository;
import com.example.easy_room.reservation.ReservationService;
import com.example.easy_room.reservation.dto.ReservationCreationDTO;
import com.example.easy_room.reservation.exceptions.ReservationDateNotAvailableException;

@SpringBootTest
public class ReservationDateNotAvailableExceptionTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelRoomService hotelRoomService;

    private HotelRoom hotelRoom;

    @BeforeEach
    void populateDB() {
        // Create and save a hotel room
        hotelRoom = new HotelRoom();
        hotelRoom.setRoomNumber("101");
        hotelRoom = hotelRoomService.saveHotelRoom(hotelRoom);

        // Create and save a reservation that will overlap with the test reservation
        Reservation reservation = new Reservation();
        reservation.setHotelRoom(hotelRoom);
        reservation.setCheckInDate(LocalDate.of(2024, 8, 20));
        reservation.setCheckOutDate(LocalDate.of(2024, 8, 25));
        reservationRepository.save(reservation);
    }

    @Test
    void shouldThrowReservationDateNotAvailableException() {
        // Prepare a ReservationCreationDTO that will cause a date overlap
        ReservationCreationDTO reservationCreationDTO = new ReservationCreationDTO(
            null,
            LocalDate.of(2024, 8, 22),
            LocalDate.of(2024, 8, 24),
            hotelRoom.getId());

        assertThrows(ReservationDateNotAvailableException.class, () -> {
            reservationService.createReservation(reservationCreationDTO);
        });
    }
}
