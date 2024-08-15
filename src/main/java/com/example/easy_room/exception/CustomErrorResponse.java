package com.example.easy_room.exception;

public record CustomErrorResponse(
    int statusCode,
    String message
) {

}
