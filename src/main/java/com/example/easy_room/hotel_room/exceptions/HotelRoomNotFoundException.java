package com.example.easy_room.hotel_room.exceptions;

public class HotelRoomNotFoundException extends RuntimeException {

    public HotelRoomNotFoundException(Long hotelRoomId) {
        super("HotelRoom not found with ID: " + hotelRoomId);
    }
}
