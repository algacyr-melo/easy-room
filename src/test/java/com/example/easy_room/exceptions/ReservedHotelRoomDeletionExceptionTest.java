package com.example.easy_room.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomRepository;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.hotel_room.exceptions.ReservedHotelRoomDeletionException;
import com.example.easy_room.reservation.Reservation;
import com.example.easy_room.reservation.ReservationRepository;

@SpringBootTest
public class ReservedHotelRoomDeletionExceptionTest {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelRoomService hotelRoomService;

    private HotelRoom hotelRoom;

    @BeforeEach
    void populateDB() {
        hotelRoom = new HotelRoom();
        hotelRoomRepository.save(hotelRoom);

        Reservation reservation = new Reservation();
        reservation.setHotelRoom(hotelRoom);
        reservationRepository.save(reservation);
    }

    @Test
    void shouldThrowReservedHotelRoomDeletionException() {

        assertThrows(ReservedHotelRoomDeletionException.class, () -> {
            hotelRoomService.deleteHotelRoom(hotelRoom.getId());
        });

    }
}
