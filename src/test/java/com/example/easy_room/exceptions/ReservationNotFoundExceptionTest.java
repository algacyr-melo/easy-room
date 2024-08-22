package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.reservation.ReservationService;
import com.example.easy_room.reservation.exceptions.ReservationNotFoundException;

@SpringBootTest
public class ReservationNotFoundExceptionTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void shouldThrowReservationNotFoundExceptionForNonExistentId() {

        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationById(42L);
        });

    }
}
