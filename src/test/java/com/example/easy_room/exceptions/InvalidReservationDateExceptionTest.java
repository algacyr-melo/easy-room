package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.reservation.ReservationService;
import com.example.easy_room.reservation.dto.ReservationCreationDTO;
import com.example.easy_room.reservation.exceptions.InvalidReservationDateException;

@SpringBootTest
public class InvalidReservationDateExceptionTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void shouldThrowInvalidReservationDateException() {
        ReservationCreationDTO reservationCreationDTO = new ReservationCreationDTO(
            "John Doe",
            LocalDate.of(2024, 8, 22), // check-in date
            LocalDate.of(2024, 8, 20), // check-out date (before check-in)
            42L);

        assertThrows(InvalidReservationDateException.class, () -> {
            reservationService.validateReservation(reservationCreationDTO);
        });
    }
}
