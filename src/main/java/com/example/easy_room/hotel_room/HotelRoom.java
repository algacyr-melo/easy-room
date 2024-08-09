package com.example.easy_room.hotel_room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

@Entity
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private boolean available;

    public HotelRoom(HotelRoomCreationDTO hotelRoomCreationDTO) {
        this.roomNumber = hotelRoomCreationDTO.roomNumber();
        this.available = true;
    }

    public void update(HotelRoomUpdateDTO hotelRoomUpdateDTO) {
        this.roomNumber = hotelRoomUpdateDTO.roomNumber();
        this.available = hotelRoomUpdateDTO.available();
    }
}
