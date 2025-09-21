package com.airport.airportPro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.airport.airportPro.entity.Pasajero;
import com.airport.airportPro.services.PasajeroService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@AllArgsConstructor
@RestController
@RequestMapping("/api/passenger")
public class PasajeroController {
    
    private final PasajeroService pasajeroService;
    
    @GetMapping({"", "/"})
    public ResponseEntity<String> defaultPage(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getone/{id}")
    public Mono<ResponseEntity<Pasajero>> getById (@PathVariable Long id){
        return pasajeroService.getById(id)
                                .map(passenger -> ResponseEntity.ok(passenger))
                                .defaultIfEmpty(ResponseEntity.notFound().build())
                                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }



    @GetMapping("/getAll")
    public ResponseEntity<Flux<Pasajero>> findAll() {
        return new ResponseEntity<>(pasajeroService.findAll(),HttpStatus.OK);
                               
    }
    
    @GetMapping("/getAll2")
    public Mono<ResponseEntity<List<Pasajero>>> findAll2() {
        return pasajeroService.findAll()
                               
                              .collectList()
                              .flatMap(passengerlist -> 
                                    Mono.just(ResponseEntity.ok(passengerlist))
                              )    
                               
                              .defaultIfEmpty(ResponseEntity.notFound().build())
                              .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
    
    
}
