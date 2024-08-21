package com.example.easy_room.reservation.dto;

import java.time.LocalDate;

import com.example.easy_room.reservation.Reservation;

public record ReservationReadDTO(
    Long Id,
    String guestName,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Long hotelRoomId
) {
    public ReservationReadDTO(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getGuestName(),
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            reservation.getHotelRoom().getId());
    }
}
