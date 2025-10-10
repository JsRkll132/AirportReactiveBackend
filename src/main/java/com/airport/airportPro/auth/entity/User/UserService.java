package com.airport.airportPro.auth.entity.User;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface UserService {

     public Mono<UserDetails> findByUsername(String username);
} 