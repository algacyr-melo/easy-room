package com.example.easy_room.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.HotelRoomService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/hotel-room")
public class HotelRoomController {

    @Autowired
    private HotelRoomService hotelRoomService;

    @Transactional
    @PostMapping
    public ResponseEntity<HotelRoomCreationDTO> createHotelRoom(
        @RequestBody
        HotelRoomCreationDTO hotelRoomCreationDTO,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        HotelRoom hotelRoom = new HotelRoom(hotelRoomCreationDTO);

        hotelRoomService.createHotelRoom(hotelRoom);

        URI location = uriComponentsBuilder
            .path("/hotel-room/{id}")
            .buildAndExpand(hotelRoom.getId())
            .toUri();

        return ResponseEntity.created(location).body(hotelRoomCreationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelRoomCreationDTO> detailHotelRoom(@PathVariable Long id) {

        HotelRoom hotelRoom = hotelRoomService.getHotelRoomById(id);
        return ResponseEntity.ok(new HotelRoomCreationDTO(hotelRoom));
    }
}
