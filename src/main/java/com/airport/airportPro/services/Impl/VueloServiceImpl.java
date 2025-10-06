package com.airport.airportPro.services.Impl;

import org.springframework.stereotype.Service;

import com.airport.airportPro.entity.Vuelo;
import com.airport.airportPro.repository.CustomVueloRepository;
import com.airport.airportPro.repository.VueloRepository;
import com.airport.airportPro.services.VueloService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class VueloServiceImpl implements VueloService {

    public final VueloRepository vueloRepository;
    public final CustomVueloRepository customVueloRepository;
    @Override
    public Mono<Vuelo> getById(Long Id) {
        return vueloRepository.findById(Id);
    }
    @Override
    public Flux<Vuelo> getAllByPassengerId(Long Id) {
        
        return customVueloRepository.getAllByPassengerId(Id);
    }
    
    @Override
    public Flux<Vuelo> getAllByAirportId(Long Id) {
        return customVueloRepository.getAllByAirportId(Id);
    }

}
