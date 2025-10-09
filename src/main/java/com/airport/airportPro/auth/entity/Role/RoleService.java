package com.airport.airportPro.auth.entity.Role;


import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class RoleService {

    public final RoleRepository roleRepository;

    public Flux<String> findRoleNamesByUserId(Long Id) {
        return roleRepository.findRoleNamesByUserId(Id);
    }
}
