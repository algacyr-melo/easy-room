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
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

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

    public Reservation(ReservationCreationDTO reservationCreationDTO) {
        this.guestName = reservationCreationDTO.guestName();
        this.checkInDate = reservationCreationDTO.checkInDate();
        this.checkOutDate = reservationCreationDTO.checkOutDate();
    }
}
