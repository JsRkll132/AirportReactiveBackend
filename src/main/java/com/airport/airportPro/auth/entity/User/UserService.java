package com.airport.airportPro.auth.entity.User;


import reactor.core.publisher.Mono;

public interface UserService {

     public Mono<MyUserDetails> findByUsername(String username);
} 