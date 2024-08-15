package com.example.easy_room.hotel_room;

import jakarta.validation.constraints.NotNull;

public record HotelRoomCreationDTO(
    @NotNull
    String roomNumber,

    @NotNull
    String roomType
) {

    public HotelRoomCreationDTO(HotelRoom hotelRoom) {
        this(hotelRoom.getRoomNumber(), hotelRoom.getRoomType());
    }
}
