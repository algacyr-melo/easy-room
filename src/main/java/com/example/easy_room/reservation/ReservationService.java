package com.example.easy_room.reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.easy_room.hotel_room.HotelRoom;
import com.example.easy_room.hotel_room.HotelRoomService;
import com.example.easy_room.reservation.dto.ReservationCreationDTO;
import com.example.easy_room.reservation.dto.ReservationReadDTO;
import com.example.easy_room.reservation.dto.ReservationUpdateDTO;
import com.example.easy_room.reservation.exceptions.InvalidReservationDateException;
import com.example.easy_room.reservation.exceptions.ReservationDateNotAvailableException;
import com.example.easy_room.reservation.exceptions.ReservationNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final HotelRoomService hotelRoomService;

    public Reservation createReservation(ReservationCreationDTO reservationCreationDTO) {
        validateReservation(reservationCreationDTO);

        if (!isReservationDateAvailable(reservationCreationDTO)) {
            String message = String.format(
                "The hotel room ID: %d is not available from %s to %s.",
                reservationCreationDTO.hotelRoomId(),
                reservationCreationDTO.checkInDate(),
                reservationCreationDTO.checkOutDate());
            throw new ReservationDateNotAvailableException(message);
        }
        HotelRoom hotelRoom = hotelRoomService
            .getHotelRoomById(reservationCreationDTO.hotelRoomId());

        Reservation reservation = new Reservation(reservationCreationDTO);
        reservation.setHotelRoom(hotelRoom);
        return reservationRepository.save(reservation);
    }

    public Page<ReservationReadDTO> getReservationsPage(Pageable pageable) {
        Page<Reservation> reservationsPage = reservationRepository.findAll(pageable);

        return reservationsPage.map(ReservationReadDTO::new);
    }

    public ReservationReadDTO getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) {
            String message = String.format("Could not find Reservation with id %d.", id);
            throw new ReservationNotFoundException(message);
        }
        return new ReservationReadDTO(reservation.get());
    }

    public Reservation updateReservation(
        ReservationUpdateDTO reservationUpdateDTO
    ) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationUpdateDTO.id());

        if (reservation.isEmpty()) {
            String message = String.format(
                "Could not update Reservation with id: %d.",
                reservationUpdateDTO.id());
            throw new ReservationNotFoundException(message);
        }
        reservation.get().update(reservationUpdateDTO);
        return reservation.get();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

    /**
     * @param reservationCreationDTO
     * @throws InvalidReservationDateException
     */
    public void validateReservation(ReservationCreationDTO reservationCreationDTO) {
        LocalDate checkInDate = reservationCreationDTO.checkInDate();
        LocalDate checkOutDate = reservationCreationDTO.checkOutDate();

        if (checkOutDate.isBefore(checkInDate)
        ) {
            throw new InvalidReservationDateException(
                "Check-out date cannot be before check-in date.");
        }

        if (checkInDate.isBefore(LocalDate.now())) {
            throw new InvalidReservationDateException(
                "Check-in date cannot be in the past.");
        }

        if (ChronoUnit.DAYS.between(checkInDate, checkOutDate) < 1) {
            throw new InvalidReservationDateException(
                "Reservation must be at least one day");
        }
    }

    /* NOTE: Could return a list of JSON overlapping dates
     * to give clear feedback to the user*/
    public boolean isReservationDateAvailable(ReservationCreationDTO reservationCreationDTO) {
        List<Reservation> reservationsOverlap = reservationRepository
            .findReservationOverlap(
                reservationCreationDTO.hotelRoomId(),
                reservationCreationDTO.checkInDate(),
                reservationCreationDTO.checkOutDate());
        return reservationsOverlap.isEmpty();
    }
}
