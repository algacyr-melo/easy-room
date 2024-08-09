package com.example.easy_room.hotel_room;

public record HotelRoomReadDTO(
    Long id,
    String roomNumber,
    boolean available
) {

    public HotelRoomReadDTO(HotelRoom hotelRoom) {
        this(hotelRoom.getId(), hotelRoom.getRoomNumber(), hotelRoom.isAvailable());
    }

}
