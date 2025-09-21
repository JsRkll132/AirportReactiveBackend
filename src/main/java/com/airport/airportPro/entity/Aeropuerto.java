package com.airport.airportPro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Aeropuerto")
public class Aeropuerto {

    
    @Id
    private Long id;
    private String nombre;                  // Nombre del aeropuerto
    private String ubicacion;               // Ciudad o región donde se encuentra
    private String codigoiata;              // Código IATA único del aeropuerto (ej. "JFK")
    private Integer numeroterminales;       // Número de terminales del aeropuerto
    private Integer numeropistas;           // Número de pistas del aeropuerto
    private String horarioapertura;         // Hora de apertura del aeropuerto (opcional)
    private String horariocierre;           // Hora de cierre del aeropuerto (opcional)
    private String estado;                  // Estado del aeropuerto (por ejemplo, "operativo", "en renovación", etc.)
    private Double latitud;                 // Coordenada de latitud del aeropuerto (opcional)
    private Double longitud;                // Coordenada de longitud del aeropuerto (opcional)
}
