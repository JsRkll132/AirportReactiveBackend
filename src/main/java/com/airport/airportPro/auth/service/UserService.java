package com.airport.airportPro.auth.service;


import com.airport.airportPro.auth.entity.MyUserDetails;

import reactor.core.publisher.Mono;

public interface UserService {

     public Mono<MyUserDetails> findByUsername(String username);
//     public Mono<MyUserDetails> 
} 