package com.airport.airportPro.services.Impl;

import org.springframework.stereotype.Service;

import com.airport.airportPro.entity.Aeropuerto;
import com.airport.airportPro.repository.AeropuertoRepository;
import com.airport.airportPro.services.AeropuertoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AeropuertoServiceImpl implements AeropuertoService {

    public final AeropuertoRepository aeropuertoRepository;
    @Override
    public Mono<Aeropuerto> getById(Long Id){
        return aeropuertoRepository.findById(Id);
    }
}
