
package com.airport.airportPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

import lombok.RequiredArgsConstructor;

 
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ServerSecurityContextRepository securityContextRepository;
    @Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
			.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**","/api/**").permitAll()
			    .anyExchange().authenticated()
			)
            .securityContextRepository(securityContextRepository)
            .httpBasic(httpbasic -> httpbasic.disable())
			.formLogin(formLogin -> formLogin.disable())
            .csrf(csrf -> csrf.disable());
		return http.build();
	}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public MapReactiveUserDetailsService userDetailsService (PasswordEncoder passwordEncoder){
        UserDetails user = User.builder()
                            .username("user")
                            .password(passwordEncoder.encode("user"))
                            .roles("USER")
                            .build();
        return new MapReactiveUserDetailsService(user);
    }


}
