package com.example.easy_room.reservation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(
        "SELECT r FROM Reservation r " +
        "WHERE r.hotelRoom.id = :hotelRoomId " +
        "AND r.checkInDate <= :checkOutDate " +
        "AND r.checkOutDate >= :checkInDate")
    List<Reservation> findReservationOverlap(
        @Param("hotelRoomId") Long hotelRoomId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate);

    boolean existsByHotelRoomId(Long hotelRoomId);
}
