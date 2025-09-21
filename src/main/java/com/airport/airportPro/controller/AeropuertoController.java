package com.airport.airportPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.airportPro.entity.Aeropuerto;
import com.airport.airportPro.services.AeropuertoService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/airport")
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    @GetMapping({"", "/"})
    public ResponseEntity<String> defaultPage(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<Mono<Aeropuerto>> getById(@PathVariable Long id){
    Mono<Aeropuerto> aeropuertoMono = aeropuertoService.getById(id);
    
    // Crear un ResponseEntity que contenga el Mono<Aeropuerto>
            ResponseEntity<Mono<Aeropuerto>> response = 
                    aeropuertoMono
                    .map(airport -> ResponseEntity.ok(Mono.just(airport)))  // Si el aeropuerto existe, devolver 200 OK
                    .defaultIfEmpty(ResponseEntity.notFound().build())      // Si no se encuentra, devolver 404 Not Found
                    .onErrorResume(e -> {                                   // Manejo de errores
                        e.printStackTrace();
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Mono.empty())); // Error 500
                    })
                    .block(); // El .block() se usa para esperar a que se complete el Mono y devolver la respuesta
            return response;
    }
}
