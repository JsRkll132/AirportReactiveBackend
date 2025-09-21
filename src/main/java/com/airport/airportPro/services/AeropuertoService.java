package com.airport.airportPro.services;

import com.airport.airportPro.entity.Aeropuerto;

import reactor.core.publisher.Mono;

public interface AeropuertoService {
    public Mono<Aeropuerto> getById(Long Id);
}
