package com.example.easy_room.hotel_room;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.easy_room.hotel_room.exceptions.HotelRoomNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class HotelRoomService {

    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoom saveHotelRoom(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom);
    }

    public List<HotelRoom> findAllHotelRoom() {
        return hotelRoomRepository.findAll();
    }

    public HotelRoom findHotelRoomById(Long id) {
        return hotelRoomRepository.findById(id)
            .orElseThrow(() -> new HotelRoomNotFoundException(id));
    }
}
