package com.airport.airportPro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.airportPro.entity.Vuelo;

import reactor.core.publisher.Mono;

@SpringBootTest
public class VueloServiceTest {

    @Autowired
    private VueloService vueloService;

    @Test
    void testgetById(){
        Mono<Vuelo> vuelos = vueloService.getById(1l);
        
        vuelos.subscribe(vuelo -> System.out.println(vuelo.toString()));
    }
}
