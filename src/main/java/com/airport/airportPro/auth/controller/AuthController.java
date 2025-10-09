package com.airport.airportPro.auth.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.airportPro.auth.controller.DTO.RoleByUserID;
import com.airport.airportPro.auth.entity.Role.RoleService;
import com.airport.airportPro.auth.entity.Role.DAO.RoleByUserIdDAO;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final RoleService roleService;
    @GetMapping("/")
    public Mono<ResponseEntity<String>> AuthPrincipalPage (AuthenticatedPrincipal principal) {
    return Mono.just(ResponseEntity.ok("Authenticated as: " + principal.getName()));
}

    @PostMapping("/login")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PostMapping("/register")
    public String postMethodName2(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/logout")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/getRolesByUser/{id}")
    public Mono<List<RoleByUserID>> findRoleNamesByUserId(@PathVariable Long id) {
        return roleService.findRoleNamesByUserId(id)
            .map(name -> new RoleByUserID(name))
            .collectList(); // << devuelve [ { "rolename": "ROLE_ADMIN" }, { "rolename": "ROLE_USER" } ]
    }
        

    
}
