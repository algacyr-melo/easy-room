package com.example.easy_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.easy_room.reservation.exceptions.InvalidReservationDateException;
import com.example.easy_room.reservation.exceptions.ReservationDateNotAvailableException;
import com.example.easy_room.reservation.exceptions.ReservationNotFoundException;

@RestControllerAdvice
public class ReservationExceptionsHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleReservationNotFoundException(
        ReservationNotFoundException e
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(ReservationDateNotAvailableException.class)
    public ResponseEntity<CustomErrorResponse> handleReservationDateNotAvailable(
        ReservationDateNotAvailableException e
    ) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(InvalidReservationDateException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidReservationDateException(
        InvalidReservationDateException e
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
