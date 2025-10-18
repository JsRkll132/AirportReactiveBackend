package com.airport.airportPro.auth.repository;


import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.airport.airportPro.auth.entity.UserRole;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public  class UserRoleRepository  {
    private final DatabaseClient databaseClient;
    

    public Mono<Void> saveUserRole(Long userId, Long roleId) {
        return databaseClient.sql("INSERT INTO user_role (user_id, role_id) VALUES ($1, $2)")
                .bind(0, userId)  // Vinculamos el parámetro userId en la posición 1
                .bind(1, roleId)  // Vinculamos el parámetro roleId en la posición 2
                .fetch()  // Indicamos que queremos ejecutar una operación de "fetch" (no esperamos resultados, solo ejecutar el SQL)
                .rowsUpdated()  // Obtenemos el número de filas afectadas por el INSERT
                .then();  // Finalizamos el proceso sin necesidad de devolver ningún resultado
    }

    // Otro ejemplo de método que puede ser útil, como obtener todos los roles por ID de usuario
    public Flux<UserRole> getUserRolesByUserId(Long userId) {
        return databaseClient.sql("SELECT user_id, role_id FROM user_role WHERE user_id = $1")
                .bind(0, userId)
                .map((row, metadata) -> new UserRole(
                        row.get("user_id", Long.class),
                        row.get("role_id", Long.class)
                ))
                .all();
    }
}  