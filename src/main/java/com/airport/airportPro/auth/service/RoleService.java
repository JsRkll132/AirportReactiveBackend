package com.airport.airportPro.auth.service;


import org.springframework.stereotype.Service;

import com.airport.airportPro.auth.repository.RoleRepository;

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
