package com.airport.airportPro.handler;


import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import com.airport.airportPro.handler.CustomExceptions.BadRegisterException;
import com.airport.airportPro.handler.ErrorDTO.ErrorResponse;

@RestControllerAdvice
public class ControllerAdvice {



    @ExceptionHandler(value = BadRegisterException.class)
    public ResponseEntity<ErrorResponse> handleBadRegisterException(BadRegisterException ex,ServerWebExchange serverWebExchange ) {
        // Elimina el stacktrace, no lo enviamos al cliente
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());  // "USER EXISTS"
        error.setMessage(ex.getMessage());  // "Error in user creation (already exist)"
        error.setStatus(ex.getStatus().value());  // 409
        error.setTimestamp(Instant.now());
        error.setPath(serverWebExchange.getRequest().getURI().toString());

        return new ResponseEntity<>(error,ex.getStatus());  // Devolvemos el código de estado adecuado (409)
    }
    // @ExceptionHandler(value = RuntimeException.class)
    // public ResponseEntity<String> runtimeExceptionHandler(RuntimeException ex){
    //     return new ResponseEntity<>("Error en la solicitud : " +
    //      HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR);

    // }
    @ExceptionHandler(value =  UnsupportedOperationException.class)
    public ResponseEntity<String> unsruntimeExceptionHandler(UnsupportedOperationException ex){
        return new ResponseEntity<>("Error en la solicitud : " +
         HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.UNAUTHORIZED);

    }
}
