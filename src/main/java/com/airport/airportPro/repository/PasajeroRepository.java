package com.airport.airportPro.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.airport.airportPro.entity.Pasajero;

public interface PasajeroRepository extends R2dbcRepository<Pasajero,Long>{
        
}
    