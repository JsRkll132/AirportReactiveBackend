package com.airport.airportPro.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<String> NoResourceFoundExceptionHandler(NoResourceFoundException ex){
        return new ResponseEntity<>("Error en la solicitud : "+ HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);

    }
}
