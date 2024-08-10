package com.example.easy_room.reservation;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReservationCreationDTO(
    @NotNull String guestName,
    @NotNull LocalDate checkInDate,
    @NotNull LocalDate checkOutDate,
    @NotNull Long hotelRoomId
) {

}
