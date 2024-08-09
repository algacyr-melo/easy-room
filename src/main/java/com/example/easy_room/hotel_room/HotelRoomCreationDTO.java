package com.example.easy_room.hotel_room;

public record HotelRoomCreationDTO(
    String roomNumber
) {

    public HotelRoomCreationDTO(HotelRoom hotelRoom) {
        this(hotelRoom.getRoomNumber());
    }
}
