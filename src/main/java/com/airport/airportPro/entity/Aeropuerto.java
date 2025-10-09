package com.airport.airportPro.entity;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("aeropuerto")
public class Aeropuerto {

    
    @Id
    @Column("id")
    private Long id;

    @Column("nombre") // Nombre del aeropuerto
    private String nombre;

    @Column("ubicacion")                  // Ciudad o región donde se encuentra
    private String ubicacion;
    
    @Column("codigoiata")               // Código IATA único del aeropuerto (ej. "JFK")
    private String codigoiata;
    
    @Column("numeroterminales")              // Número de terminales del aeropuerto
    private Integer numeroterminales;
    
    @Column("numeropistas")       // Número de pistas del aeropuerto
    private Integer numeropistas;
    
    @Column("horarioapertura")           // Hora de apertura del aeropuerto (opcional)
    private LocalTime horarioapertura;
    
    @Column("horariocierre")         // Hora de cierre del aeropuerto (opcional)
    private LocalTime horariocierre;
    
    @Column("estado")           // Estado del aeropuerto (por ejemplo, "operativo", "en renovación", etc.)
    private String estado;
    
    @Column("latitud")                  // Coordenada de latitud del aeropuerto (opcional)
    private Double latitud;
    
    @Column("longitud")                 // Coordenada de longitud del aeropuerto (opcional)
    private Double longitud;                
}
