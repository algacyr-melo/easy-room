package com.example.easy_room.hotel_room;

public record HotelRoomCreationDTO(
    String roomNumber,
    String roomType
) {

    public HotelRoomCreationDTO(HotelRoom hotelRoom) {
        this(hotelRoom.getRoomNumber(), hotelRoom.getRoomType());
    }
}
