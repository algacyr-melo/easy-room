package com.example.easy_room.hotel_room;

import com.example.easy_room.hotel_room.dto.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.dto.HotelRoomUpdateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private String roomType;

    public HotelRoom(HotelRoomCreationDTO hotelRoomCreationDTO) {
        this.roomNumber = hotelRoomCreationDTO.roomNumber();
        this.roomType = hotelRoomCreationDTO.roomType();
    }

    public void update(HotelRoomUpdateDTO hotelRoomUpdateDTO) {
        this.roomNumber = hotelRoomUpdateDTO.roomNumber();
        this.roomType = hotelRoomUpdateDTO.roomType();
    }
}
