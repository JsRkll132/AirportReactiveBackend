package com.airport.airportPro.auth.entity.Role;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


import reactor.core.publisher.Flux;

public interface RoleRepository extends R2dbcRepository<Role,Long> {
    @Query("""
        SELECT r.name FROM role r
        JOIN user_role ur ON ur.role_id = r.id
        WHERE ur.user_id = :userId
    """)
    Flux<String> findRoleNamesByUserId(Long userId);
}
