package com.airport.airportPro.auth.service;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class JwtService {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long jwtRefreshTokenExpiration;

    private SecretKey getSingInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey) );
    }

    public String generateToken(final UserDetails user){
        return buildToken(user, jwtTokenExpiration,"access");
    }
    public String generateRefreshToken(final UserDetails user){
        return buildToken(user, jwtRefreshTokenExpiration,"refresh");
    }

    private String buildToken(final UserDetails user, final long time, final String typetoken){
            
            return Jwts.builder()
                    .subject(user.getUsername())
                    .claims(Map.of("name",user.getUsername(),"email",user.getAuthorities()))
                    .claim("typ", typetoken)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + time))
                    .signWith(getSingInKey(), Jwts.SIG.HS256 )
                    .compact();
    }
}
