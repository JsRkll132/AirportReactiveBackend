package com.airport.airportPro.repository;

import com.airport.airportPro.entity.Vuelo;

import reactor.core.publisher.Flux;

public interface CustomVueloRepository {
    public Flux<Vuelo> getAllByPassengerId(Long Id);
    public Flux<Vuelo> getAllByAirportId(Long Id);
}
