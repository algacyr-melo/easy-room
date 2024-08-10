package com.example.easy_room.hotel_room;

import jakarta.validation.constraints.NotNull;

public record HotelRoomUpdateDTO(
    @NotNull Long id,
    @NotNull String roomNumber,
    @NotNull String roomType
) {

}
