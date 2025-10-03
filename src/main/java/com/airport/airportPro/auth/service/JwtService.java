package com.airport.airportPro.auth.service;

import org.springframework.beans.factory.annotation.Value;

import com.airport.airportPro.auth.entity.User.User;

public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long jwtRefreshTokenExpiration;


    public String generateToken(final User user){
        return buildToken(user, jwtTokenExpiration);
    }
    public String generateRefreshToken(final User user){
        return buildToken(user, jwtRefreshTokenExpiration);
    }

    private String buildToken(final User user, final long time){
        return "";
    }
}
