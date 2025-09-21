package com.airport.airportPro.services;

import com.airport.airportPro.entity.Vuelo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VueloService {
    public Mono<Vuelo> getById(Long Id);
    public Flux<Vuelo> getAllByPassengerId(Long Id);
    public Flux<Vuelo> getAllByAirportId(Long Id);

}
