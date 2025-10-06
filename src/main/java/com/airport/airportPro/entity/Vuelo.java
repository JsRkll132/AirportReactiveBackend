package com.airport.airportPro.entity;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor      
@AllArgsConstructor
@Table("vuelo")
public class Vuelo {

        @Id
        @Column("id")
        private Long id;

        @Column("numerovuelo")
        private String numerovuelo;

        @Column("aeropuertosalidaid")
        private Long aeropuertosalidaId;

        @Column("aeropuertodestinoid")
        private Long aeropuertodestinoId;

        @Column("horasalida")
        private LocalTime horasalida;   // ideal: LocalTime/LocalDateTime

        @Column("horallegada")
        private LocalTime horallegada;  // ideal: LocalTime/LocalDateTime

        @Transient private String aeropuertoSalida;
        @Transient private String ubicacionSalida;
        @Transient private String aeropuertoDestino;
        @Transient private String ubicacionDestino;        
  
     public Vuelo(Long id,
                 String numerovuelo,
                 Long aeropuertosalidaId,
                 Long aeropuertodestinoId,
                 LocalTime horasalida,
                 LocalTime horallegada) {
        this.id = id;
        this.numerovuelo = numerovuelo;
        this.aeropuertosalidaId = aeropuertosalidaId;
        this.aeropuertodestinoId = aeropuertodestinoId;
        this.horasalida = horasalida;
        this.horallegada = horallegada;
    }       
  
    
}
