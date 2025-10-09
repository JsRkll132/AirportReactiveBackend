package com.airport.airportPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.airportPro.entity.Vuelo;
import com.airport.airportPro.services.VueloService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
@AllArgsConstructor
public class VueloController {
    private final VueloService vueloService;

    @GetMapping({"", "/"})
    public Mono<ResponseEntity<String>> defaultPage(){
        return Mono.just(ResponseEntity.notFound().build()) ;
    }


    @GetMapping("/getone/{id}")
    public Mono<ResponseEntity<Vuelo>> getById (@PathVariable Long id){
        return vueloService.getById(id)
                                .map(flight -> ResponseEntity.ok(new Vuelo(flight.getId(), flight.getNumerovuelo(), flight.getAeropuertosalidaId(), 
                                flight.getAeropuertodestinoId(),flight.getHorasalida(), flight.getHorallegada())))
                                .defaultIfEmpty(ResponseEntity.notFound().build())
                                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping("/getByPassenger/{id}")
    public ResponseEntity<Flux<Vuelo>> getAllByPassengerId(@PathVariable Long id){
        return ResponseEntity.ok(vueloService.getAllByPassengerId(id));                            
    }
    
    @GetMapping("/getByAirport/{id}")
    public ResponseEntity<Flux<Vuelo>> getAllByAirportId(@PathVariable Long id){
        return new ResponseEntity<>(vueloService.getAllByAirportId(id),HttpStatus.OK);
    }     
}
