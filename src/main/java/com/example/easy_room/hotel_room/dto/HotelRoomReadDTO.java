package com.example.easy_room.hotel_room.dto;

import com.example.easy_room.hotel_room.HotelRoom;

public record HotelRoomReadDTO(
    Long id,
    String roomNumber,
    String roomType
) {
    public HotelRoomReadDTO(HotelRoom hotelRoom) {
        this(
            hotelRoom.getId(),
            hotelRoom.getRoomNumber(),
            hotelRoom.getRoomType());
    }
}
