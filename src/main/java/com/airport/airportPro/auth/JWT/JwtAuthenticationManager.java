package com.airport.airportPro.auth.JWT;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.airport.airportPro.handler.CustomExceptions.CustomException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager{



    private final JwtService jwtService ;

 
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                    .map(auth -> jwtService.getJwtTokenClaims(auth.getCredentials().toString()))
                    .log()
                    .onErrorResume(e -> Mono.error(new CustomException("Invalid Authentication", "BAD_CREDENTIALS", HttpStatus.UNAUTHORIZED)))
                    .map(claims -> new UsernamePasswordAuthenticationToken(
                                            claims.getSubject() , 
                                            null,
                                            Stream.of(claims.get("roles"))
                                                    .map(role -> (List<Map<String,String>>) role)
                                                    .flatMap(role -> role.stream()
                                                                        .map(r -> r.get("authority"))
                                                                        .map(SimpleGrantedAuthority::new))
                                                    .collect(Collectors.toList()))
                    );              
    }

}
