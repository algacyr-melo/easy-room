package com.example.easy_room.hotel_room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

    Optional<HotelRoom> findByRoomNumber(String roomNumber);
}
