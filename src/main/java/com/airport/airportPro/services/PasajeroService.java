package com.airport.airportPro.services;


import com.airport.airportPro.entity.Pasajero;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PasajeroService {
    public Mono<Pasajero> getById(Long Id);
    public Flux<Pasajero> findAll();
}
