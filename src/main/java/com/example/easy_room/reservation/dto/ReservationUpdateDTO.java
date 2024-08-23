package com.example.easy_room.reservation.dto;

import jakarta.validation.constraints.NotNull;

public record ReservationUpdateDTO(
    @NotNull Long id,
    @NotNull String guestName
) {
}
