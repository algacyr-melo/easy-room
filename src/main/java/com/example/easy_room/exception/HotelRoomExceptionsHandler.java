package com.example.easy_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.easy_room.hotel_room.exceptions.HotelRoomDuplicateException;
import com.example.easy_room.hotel_room.exceptions.HotelRoomNotFoundException;
import com.example.easy_room.hotel_room.exceptions.ReservedHotelRoomDeletionException;

@RestControllerAdvice
public class HotelRoomExceptionsHandler {

    @ExceptionHandler(HotelRoomNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleHotelRoomNotFound(
        HotelRoomNotFoundException e
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(HotelRoomDuplicateException.class)
    public ResponseEntity<CustomErrorResponse> handleHotelRoomDuplicateException(
        HotelRoomDuplicateException e
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(ReservedHotelRoomDeletionException.class)
    public ResponseEntity<CustomErrorResponse> handleReservedHotelRoomDeletionException(
        ReservedHotelRoomDeletionException e
    ) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            httpStatus.value(),
            e.getMessage()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
