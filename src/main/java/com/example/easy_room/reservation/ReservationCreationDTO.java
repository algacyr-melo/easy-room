package com.example.easy_room.reservation;

import java.time.LocalDate;

public record ReservationCreationDTO(
    String guestName,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Long hotelRoomId
) {

}
