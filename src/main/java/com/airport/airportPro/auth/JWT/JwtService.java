package com.airport.airportPro.auth.JWT;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.airport.airportPro.auth.entity.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
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

    public String generateAccessToken(final MyUserDetails user){
        return buildToken(user, jwtTokenExpiration,"access");
    }
    public String generateRefreshToken(final MyUserDetails user){
        return buildToken(user, jwtRefreshTokenExpiration,"refresh");
    }

    public Claims getJwtTokenClaims(String token) { 
        return  Jwts.parser().verifyWith(getSingInKey()).build().parseSignedClaims(token).getPayload();
    }   
    
    public boolean IsTokenValid(final String token, final MyUserDetails user){

        try {
            final String username  = getJwtTokenClaims(token).getSubject();
            return username.equals(user.getUsername()) || username.equals(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("some error in token validation");
        }
        return false;
    }

    public String buildToken(final MyUserDetails user, final long time, final String typetoken){
            log.info(getSingInKey().toString());
            return Jwts.builder()
                    .subject(user.getUsername())
                    .claims(Map.of("username",user.getUsername(),"email",user.getEmail(),
                    "roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()))
                    .claim("typ", typetoken)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + time))
                    .signWith(getSingInKey(), Jwts.SIG.HS256 )
                    .compact();
    }
}
