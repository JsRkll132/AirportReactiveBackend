package com.airport.airportPro.auth.service;


import com.airport.airportPro.auth.controller.DTO.LoginDTO;
import com.airport.airportPro.auth.controller.DTO.RegisterDTO;
import com.airport.airportPro.auth.entity.MyUserDetails;

import reactor.core.publisher.Mono;

public interface UserService {

     public Mono<MyUserDetails> findByUsername(String username);
     public Mono<String> userLongIn(LoginDTO loginDTO);
     public Mono<String> userRegister(RegisterDTO registerDTO);
//     public Mono<MyUserDetails> 
} 