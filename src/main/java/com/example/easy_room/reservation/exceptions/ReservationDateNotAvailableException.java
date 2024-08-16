package com.example.easy_room.reservation.exceptions;

import java.time.LocalDate;

public class ReservationDateNotAvailableException extends RuntimeException {

    public ReservationDateNotAvailableException(
        Long hotelRoomId,
        LocalDate checkInDate,
        LocalDate checkOutDate
    ) {
        super(String.format(
            "The reservation could not be created. The hotel room ID: %d is not available from %s to %s.",
            hotelRoomId,
            checkInDate,
            checkOutDate
        ));
    }
}
