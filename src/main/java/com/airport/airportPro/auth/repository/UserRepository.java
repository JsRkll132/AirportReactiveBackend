package com.airport.airportPro.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.airport.airportPro.auth.entity.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User,Long>{

    Mono<User> findByUsername(String username);
    Mono<User> findByUsernameOrEmail(String username, String email);
}  