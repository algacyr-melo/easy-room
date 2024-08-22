package com.example.easy_room.hotel_room;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.easy_room.hotel_room.dto.HotelRoomCreationDTO;
import com.example.easy_room.hotel_room.dto.HotelRoomReadDTO;
import com.example.easy_room.hotel_room.dto.HotelRoomUpdateDTO;
import com.example.easy_room.hotel_room.exceptions.HotelRoomDuplicateException;
import com.example.easy_room.hotel_room.exceptions.HotelRoomNotFoundException;
import com.example.easy_room.hotel_room.exceptions.ReservedHotelRoomDeletionException;
import com.example.easy_room.reservation.ReservationRepository;
import com.example.easy_room.reservation.ReservationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class HotelRoomService {

    private final HotelRoomRepository hotelRoomRepository;

    private final ReservationRepository reservationRepository;

    public HotelRoom createHotelRoom(HotelRoomCreationDTO hotelRoomCreationDTO) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository
            .findByRoomNumber(hotelRoomCreationDTO.roomNumber());

        if (hotelRoom.isPresent()) {
            throw new HotelRoomDuplicateException(
                "Could not create HotelRoom with duplicated room numbers.");
        }
        return hotelRoomRepository.save(new HotelRoom(hotelRoomCreationDTO));
    }

    public List<HotelRoomReadDTO> getHotelRoomList() {
        List<HotelRoom> hotelRoomList = hotelRoomRepository.findAll();

        return hotelRoomList.stream()
            .map(HotelRoomReadDTO::new)
            .toList();
    }

    public HotelRoom getHotelRoomById(Long id) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(id);
        if (hotelRoom.isEmpty()) {
            String message = String.format("Could not find HotelRoom with id: %d.", id);
            throw new HotelRoomNotFoundException(message);
        }
        return hotelRoom.get();
    }

    public HotelRoom updateHotelRoom(HotelRoomUpdateDTO hotelRoomUpdateDTO) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(hotelRoomUpdateDTO.id());

        if (hotelRoom.isEmpty()) {
            String message = String.format(
                "Could not update HotelRoom with id: %d.",
                hotelRoomUpdateDTO.id());
            throw new HotelRoomNotFoundException(message);
        }
        hotelRoom.get().update(hotelRoomUpdateDTO);
        return hotelRoom.get();
    }

    public void deleteHotelRoom(Long id) {
        // Prevent deletion of hotel rooms with active reservations
        if (reservationRepository.existsByHotelRoomId(id)) {
            String message = "Could not delete hotel room with active reservations";
            throw new ReservedHotelRoomDeletionException(message);
        }
        hotelRoomRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return hotelRoomRepository.existsById(id);
    }
}
