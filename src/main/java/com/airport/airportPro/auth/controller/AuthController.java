package com.airport.airportPro.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/")
    public Mono<ResponseEntity<String>> AuthPrincipalPage (AuthenticatedPrincipal principal) {
    return Mono.just(ResponseEntity.ok("Authenticated as: " + principal.getName()));
}

    @PostMapping("/login")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PostMapping("/register")
    public String postMethodName2(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/logout")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    
}
