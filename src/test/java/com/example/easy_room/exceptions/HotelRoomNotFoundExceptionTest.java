package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.hotel_room.exceptions.HotelRoomNotFoundException;

@SpringBootTest
public class HotelRoomNotFoundExceptionTest {

    @Autowired
    private HotelRoomService hotelRoomService;

    @Test
    void shouldThrowHotelRoomNotFoundExceptionForNonExistentId() {

        assertThrows(HotelRoomNotFoundException.class, () -> {
            hotelRoomService.getHotelRoomById(42L);
        });

    }
}
