package com.airport.airportPro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Pasajero")
public class Pasajero {

    @Id
    @Column("id")
    private Long id;
    @Column("nombre")
    private String nombre;
    @Column("dni")
    private String dni;
 //   private Long vueloId; // Relación con vuelo
    
}
