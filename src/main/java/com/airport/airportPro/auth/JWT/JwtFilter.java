package com.airport.airportPro.auth.JWT;


import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.airport.airportPro.handler.CustomExceptions.CustomException;

import io.netty.handler.codec.http.HttpHeaderNames;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest serverHttpRequest = (ServerHttpRequest)exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        if (path.startsWith("/auth/") || path.equals("/auth")) { 
            return chain.filter(exchange);
        }
        // System.out.println(serverHttpRequest.getHeaders().toString()
        String auth  = serverHttpRequest.getHeaders().getFirst(HttpHeaderNames.AUTHORIZATION.toString());
      //  System.out.println(auth);
        if (auth == null ){ 
            return  Mono.error(new CustomException("Error in authentication",
                                 "INCORRECT_AUTHORIZATION", HttpStatus.UNAUTHORIZED)) ;
        }
        if (!auth.startsWith("Bearer ")){
            return   Mono.error(new CustomException("Error in authentication",
                                 "INCORRECT_AUTHORIZATION", HttpStatus.UNAUTHORIZED));    
        }
        String token = auth.replace("Bearer ", "");
        exchange.getAttributes().put("TOKEN", token);
        return chain.filter(exchange);
    }

}
