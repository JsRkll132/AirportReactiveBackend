
package com.airport.airportPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.airport.airportPro.auth.JWT.JwtFilter;

import lombok.RequiredArgsConstructor;

 
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

 
    private final SecurityContextRepository securityContextRepository;
    @Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
		http
			.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**","/api/**").permitAll()
			    .anyExchange().authenticated()
			)
            .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
            .securityContextRepository(securityContextRepository)
            .httpBasic(httpbasic -> httpbasic.disable())
			.formLogin(formLogin -> formLogin.disable())
            .logout(logout -> logout.disable())
            .csrf(csrf -> csrf.disable());
		return http.build();
	}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    // @Bean
    // public MapReactiveUserDetailsService userDetailsService (PasswordEncoder passwordEncoder){
    //     UserDetails user = User.builder()
    //                         .username("user")
    //                         .password(passwordEncoder.encode("user"))
    //                         .roles("USER")
    //                         .build();
    //     return new MapReactiveUserDetailsService(user);
    // }


}
