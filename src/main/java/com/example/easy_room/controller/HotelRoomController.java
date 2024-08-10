package com.example.easy_room.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.HotelRoomReadDTO;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.hotel_room.HotelRoomUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/hotel-room")
public class HotelRoomController {

    private final HotelRoomService hotelRoomService;

    @Transactional
    @PostMapping
    public ResponseEntity<HotelRoomCreationDTO> createHotelRoom(
        @RequestBody
        HotelRoomCreationDTO hotelRoomCreationDTO,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        HotelRoom hotelRoom = new HotelRoom(hotelRoomCreationDTO);

        hotelRoomService.saveHotelRoom(hotelRoom);

        URI location = uriComponentsBuilder
            .path("/hotel-room/{id}")
            .buildAndExpand(hotelRoom.getId())
            .toUri();

        return ResponseEntity.created(location).body(hotelRoomCreationDTO);
    }

    // TODO: add pagination
    @GetMapping
    public  ResponseEntity<List<HotelRoomReadDTO>> getHotelRooms() {

        List<HotelRoom> hotelRooms = hotelRoomService.findAllHotelRoom();

        List<HotelRoomReadDTO> hotelRoomsDTO = hotelRooms.stream()
            .map(HotelRoomReadDTO::new)
            .toList();

        return ResponseEntity.ok(hotelRoomsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelRoomReadDTO> detailHotelRoom(@PathVariable Long id) {

        HotelRoom hotelRoom = hotelRoomService.getHotelRoomById(id);
        return ResponseEntity.ok(new HotelRoomReadDTO(hotelRoom));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<HotelRoomReadDTO> updateHotelRoom(
        @Valid @RequestBody HotelRoomUpdateDTO hotelRoomUpdateDTO
    ) {
        HotelRoom hotelRoom = hotelRoomService.getHotelRoomById(hotelRoomUpdateDTO.id());

        hotelRoom.update(hotelRoomUpdateDTO);
        return ResponseEntity.ok(new HotelRoomReadDTO(hotelRoom));
    }

    // TODO: Delete mapping
}
