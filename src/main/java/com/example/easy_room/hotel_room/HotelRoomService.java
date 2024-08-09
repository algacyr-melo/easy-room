package com.example.easy_room.hotel_room;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class HotelRoomService {
    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoom createHotelRoom(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom);
    }

    public List<HotelRoom> findAll() {
        return hotelRoomRepository.findAll();
    }
}
