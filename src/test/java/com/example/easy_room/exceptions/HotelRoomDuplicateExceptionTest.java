package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomRepository;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.hotel_room.dto.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.exceptions.HotelRoomDuplicateException;

@SpringBootTest
public class HotelRoomDuplicateExceptionTest {

    @Autowired
    private HotelRoomService hotelRoomService;

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    private final String roomNumberDup = "42";

    @BeforeEach
    void createHotelRoom() {
        // Create and save a hotel room
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setRoomNumber(roomNumberDup);
        hotelRoomRepository.save(hotelRoom);
    }

    @Test
    void shouldThrowHotelRoomDuplicateException() {
        // Try to create a hotel room with a duplicate number
        HotelRoomCreationDTO hotelRoomCreationDTO = new HotelRoomCreationDTO(
            roomNumberDup,
            "SUITE");

        assertThrows(HotelRoomDuplicateException.class, () -> {
            hotelRoomService.createHotelRoom(hotelRoomCreationDTO);
        });
    }
}
