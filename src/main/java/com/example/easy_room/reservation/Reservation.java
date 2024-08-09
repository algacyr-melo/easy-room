package com.example.easy_room.reservation;
import java.time.LocalDate;

import com.example.easy_room.hotel_room.HotelRoom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne
    private HotelRoom hotelRoom;
}
