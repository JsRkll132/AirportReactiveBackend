package com.airport.airportPro.handler.ErrorDTO;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class ErrorResponse{
    private String errorCode;
    private String message;
    private int status;
    private Instant timestamp;
    private String path;
}
