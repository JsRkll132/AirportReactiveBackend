package com.airport.airportPro.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.airport.airportPro.auth.JWT.JwtService;
import com.airport.airportPro.auth.entity.MyUserDetails;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class JwtServiceTest {
    @Mock
    private RoleService roleService;

    // Inyectamos las dependencias en JwtService (que usa RoleService)
    @InjectMocks
    private JwtService jwtService;

    @Test
    void testIsTokenValid() {

    }

    @Test
    void testGenerateRefreshToken() {

    }

    @Test
    void testGenerateToken() {

    }

    @Test
    void testGetJwtTokenClaims() {

    }

    @Test
    void testIsTokenValid2() {
        
    }

    @Test
    void testBuildToken() {
        RoleService roleService = Mockito.mock(RoleService.class);
        Flux<String> roledata = Flux.just("ROLE_USER", "ROLE_ADMIN"); // Simulamos los roles para el usuario
        Mockito.when(roleService.findRoleNamesByUserId(1L)).thenReturn(roledata);
         List<SimpleGrantedAuthority> authorities = roledata
                .map(SimpleGrantedAuthority::new)   // Transformamos cada role a SimpleGrantedAuthority
                .collect(Collectors.toList())       // Recogemos los resultados en una lista
                .block();                 
    
        MyUserDetails myUserDetails = new MyUserDetails("aeasad", "awdawdawddaw", "wqwq@gmail.com",
                true, true, true, true, authorities);

        // Crear el servicio de token (supongo que existe una clase llamada TokenService)
        JwtService tokenService = new JwtService();

        // Generamos el token
        String token = tokenService.buildToken(myUserDetails, 3600000, "JWT");        
        System.out.println((token));
    }

    @Test
    void testGenerateRefreshToken2() {
        
    }

    @Test
    void testGenerateToken2() {
        
    }

    @Test
    void testGetJwtTokenClaims2() {
        
    }
}
