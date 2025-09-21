package com.airport.airportPro.services.Impl;


import org.springframework.stereotype.Service;

import com.airport.airportPro.entity.Pasajero;
import com.airport.airportPro.repository.PasajeroRepository;
import com.airport.airportPro.services.PasajeroService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service        
public class PasajeroServiceImpl implements PasajeroService{

    public final PasajeroRepository pasajeroRepository;
    @Override
    public Mono<Pasajero> getById(Long Id) {
        return pasajeroRepository.findById(Id);
    }
    @Override
    public Flux<Pasajero> findAll() {
        return pasajeroRepository.findAll();
    }

}
