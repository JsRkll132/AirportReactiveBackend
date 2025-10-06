package com.airport.airportPro.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.airport.airportPro.entity.Vuelo;

public interface VueloRepository  extends R2dbcRepository<Vuelo,Long> {

}
