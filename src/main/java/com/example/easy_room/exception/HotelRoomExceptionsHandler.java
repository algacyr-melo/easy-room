package com.example.easy_room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.easy_room.hotel_room.exceptions.HotelRoomNotFoundException;

@RestControllerAdvice
public class HotelRoomExceptionsHandler {

    @ExceptionHandler(HotelRoomNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleHotelRoomNotFound(HotelRoomNotFoundException e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
