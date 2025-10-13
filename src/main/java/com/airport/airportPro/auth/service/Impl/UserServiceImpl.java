package com.airport.airportPro.auth.service.Impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.airport.airportPro.auth.controller.DTO.LoginDTO;
import com.airport.airportPro.auth.entity.MyUserDetails;
import com.airport.airportPro.auth.repository.RoleRepository;
import com.airport.airportPro.auth.repository.UserRepository;
import com.airport.airportPro.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @Override
    public Mono<MyUserDetails> findByUsername(String username) {
      return userRepository.findByUsername(username)
        .switchIfEmpty(Mono.error(new UsernameNotFoundException("No existe")))
        .flatMap(u ->
          roleRepository.findRoleNamesByUserId(u.getId())
            .map(SimpleGrantedAuthority::new)
            .collectList()
            .map(auths -> new MyUserDetails(u.getUsername(), u.getPassword(), u.getEmail(), 
            true, true, true, true, auths)
            )
        );
    }


    @Override
    public Mono<String> userLongIn(LoginDTO loginDTO) {
        /*return userRepository.findByUsernameOrEmail(loginDTO.username(), loginDTO.username())
                              .filter(user -> passwordEncoder.matches(loginDTO.password(), user.getPassword()))
                              .flatMap(u -> 
                                        roleRepository.findRoleNamesByUserId(u.getId())
                                      .map(SimpleGrantedAuthority::new)
                                      .collectList()
                                      .map(auths_ -> new MyUserDetails(u.getUsername(),u.getPassword(),u.getEmail(),true,true,true,true,auths_))
                              )
                              .map() */  
                    
                            return null;   
                        
                      
    }      

    
}
