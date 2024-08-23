package com.example.easy_room.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.hotel_room.dto.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.dto.HotelRoomReadDTO;
import com.example.easy_room.hotel_room.dto.HotelRoomUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rooms")
public class HotelRoomController {

    private final HotelRoomService hotelRoomService;

    @Transactional
    @PostMapping
        public ResponseEntity<HotelRoomReadDTO> createHotelRoom(
        @Valid @RequestBody
        HotelRoomCreationDTO hotelRoomCreationDTO,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        HotelRoom hotelRoom = hotelRoomService.createHotelRoom(hotelRoomCreationDTO);

        URI location = uriComponentsBuilder
            .path("/hotel-room/{id}")
            .buildAndExpand(hotelRoom.getId())
            .toUri();

        return ResponseEntity.created(location).body(new HotelRoomReadDTO(hotelRoom));
    }

    @GetMapping
    public  ResponseEntity<List<HotelRoomReadDTO>> getHotelRoomList() {
        List<HotelRoomReadDTO> hotelRoomList = hotelRoomService.getHotelRoomList();

        return ResponseEntity.ok(hotelRoomList);
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
        HotelRoom hotelRoom = hotelRoomService.updateHotelRoom(hotelRoomUpdateDTO);

        return ResponseEntity.ok(new HotelRoomReadDTO(hotelRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelRoom(@PathVariable Long id) {
        if (!hotelRoomService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hotelRoomService.deleteHotelRoom(id);
        return ResponseEntity.noContent().build();
    }
}
