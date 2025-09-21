package com.airport.airportPro.repository.Impl;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.airport.airportPro.entity.Vuelo;
import com.airport.airportPro.repository.CustomVueloRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class CustomVueloRepositoryImpl implements CustomVueloRepository{

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Vuelo> getAllByPassengerId(Long Id){
        return databaseClient.sql("SELECT v.id , v.numerovuelo,v.aeropuertosalidaid ,\n" + //
                        "\tv.aeropuertodestinoid , v.horasalida, v.horallegada FROM pasajero_vuelo pv\n" + //
                        "INNER JOIN vuelo v on  v.id = pv.id_vuelo\n" + //
                        "WHERE id_pasajero = :Id")
                .bind("Id",Id)
                .map((row, metadata) -> new Vuelo(
                        row.get("id", Long.class ),
                        row.get("numerovuelo", String.class),
                        row.get("aeropuertosalidaid", Long.class),
                        row.get("aeropuertodestinoid", Long.class),
                        row.get("horasalida", String.class),
                        row.get("horallegada", String.class)
                ))
                .all()
                .onErrorResume(e -> Flux.empty());  // `all()` para devolver todos los pasajeros
    }

    @Override
    public Flux<Vuelo> getAllByAirportId(Long Id){
        return databaseClient.sql("SELECT v.id AS vuelo_id, " +
                                  "v.numeroVuelo, " +
                                  "a_s.id AS aeropuerto_salidaid, " +
                                  "a_d.id AS aeropuerto_destinoid, " +
                                  "a_s.nombre AS aeropuerto_salida, " +
                                  "a_s.ubicacion AS ubicacion_salida, " +
                                  "a_d.nombre AS aeropuerto_destino, " +
                                  "a_d.ubicacion AS ubicacion_destino, " +
                                  "v.horaSalida, " +
                                  "v.horaLlegada " +
                                  "FROM Vuelo v " +
                                  "LEFT JOIN Aeropuerto a_s ON v.aeropuertoSalidaId = a_s.id " +
                                  "LEFT JOIN Aeropuerto a_d ON v.aeropuertoDestinoId = a_d.id " +
                                  "WHERE v.aeropuertoSalidaId = :Id OR v.aeropuertoDestinoId = :Id " +
                                  "ORDER BY v.horaSalida")
                .bind("Id", Id)
                .map((row, metadata) -> new Vuelo(
                        row.get("vuelo_id", Long.class),
                        row.get("numeroVuelo", String.class),
                        row.get("aeropuerto_salidaid", Long.class),
                        row.get("aeropuerto_destinoid", Long.class),
                        row.get("horaSalida", String.class),
                        row.get("horaLlegada", String.class),
                        row.get("aeropuerto_salida", String.class),
                        row.get("ubicacion_salida", String.class),
                        row.get("aeropuerto_destino", String.class),
                        row.get("ubicacion_destino", String.class)
                        
                ))
                .all()
                .onErrorResume(e -> { e.printStackTrace(); return Flux.empty() ;} );  // `all()` para devolver todos los pasajeros
    }
}
