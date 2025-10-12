package com.airport.airportPro.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.airport.airportPro.auth.entity.Token;

public interface TokenRepository extends R2dbcRepository < Token , Long>   {

}
