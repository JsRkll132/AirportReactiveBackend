package com.airport.airportPro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor      
@AllArgsConstructor
@Table("Vuelos")
public class Vuelo {

    @Id
    private Long id;
    private String numeroVuelo;
    private Long aeropuertoSalidaId;
    private Long aeropuertoDestinoId;
    private String horaSalida;
    private String horaLlegada;
    private String aeropuertoSalida;
    private String ubicacionSalida;
    private String aeropuertoDestino;
    private String ubicacionDestino;



    public Vuelo(Long id, String numeroVuelo, Long aeropuertoSalidaId, Long aeropuertoDestinoId, 
                 String horaSalida, String horaLlegada) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.aeropuertoSalidaId = aeropuertoSalidaId;
        this.aeropuertoDestinoId = aeropuertoDestinoId;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }
        
  
    
}
