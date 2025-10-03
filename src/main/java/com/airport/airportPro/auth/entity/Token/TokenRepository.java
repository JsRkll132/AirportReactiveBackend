package com.airport.airportPro.auth.entity.Token;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TokenRepository extends R2dbcRepository < Token , Long>   {

}
