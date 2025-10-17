package com.airport.airportPro.handler.CustomExceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRegisterException extends RuntimeException{
    private final String errorCode;
    private final HttpStatus status;
    public BadRegisterException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
