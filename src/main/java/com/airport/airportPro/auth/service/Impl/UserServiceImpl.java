package com.airport.airportPro.auth.service.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.airport.airportPro.auth.JWT.JwtService;
import com.airport.airportPro.auth.controller.DTO.LoginDTO;
import com.airport.airportPro.auth.controller.DTO.RegisterDTO;
import com.airport.airportPro.auth.controller.DTO.TokenDTO;
import com.airport.airportPro.auth.entity.MyUserDetails;
import com.airport.airportPro.auth.repository.RoleRepository;
import com.airport.airportPro.auth.repository.UserRepository;
import com.airport.airportPro.auth.repository.UserRoleRepository;
import com.airport.airportPro.auth.service.UserService;
import com.airport.airportPro.handler.CustomExceptions.CustomException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
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
    public Mono<TokenDTO> userLongIn(LoginDTO loginDTO) {
        return userRepository.findByUsernameOrEmail(loginDTO.username(), loginDTO.username()).next()
                              .filter(user -> passwordEncoder.matches(loginDTO.password(), user.getPassword()) )
                              .switchIfEmpty(Mono.error(new Throwable("Error")))
                              .flatMap(u -> 
                                        roleRepository.findRoleNamesByUserId(u.getId())
                                      .map(SimpleGrantedAuthority::new)
                                      .collectList()
                                      .map(auths_ -> new MyUserDetails(u.getUsername(),u.getPassword(),u.getEmail(),true
                                      ,true,true,true,auths_)
                                          )
                              )
                              .flatMap(u -> Mono.just(new TokenDTO(jwtService.generateAccessToken(u)) ))
                              .onErrorResume(e -> Mono.error(new CustomException("Error in login", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED)  ))
                    
                              ;
                    
                            //return null;   
                        
                      
    }


    @Override
    public Mono<String> userRegister(RegisterDTO registerDTO) {
        com.airport.airportPro.auth.entity.User myUserCreated = com.airport.airportPro.auth.entity.User.builder()
                .email(registerDTO.email())
                .username(registerDTO.username())
                .password(passwordEncoder.encode(registerDTO.password()))
                .build();

        // Verificamos si el usuario ya existe
        Mono<Boolean> userExist = userRepository.findByUsernameOrEmail(myUserCreated.getUsername(), myUserCreated.getEmail()).hasElements();

        return userExist.flatMap(exists -> {
                              if (exists) {
                                  // Si el usuario ya existe, lanzamos una BadRegisterException
                                  return Mono.error(new CustomException("Error in user creation (already exists)", "USER_EXIST_CONFLICT" , HttpStatus.CONFLICT));
                              }
                              // Si no existe, guardamos al nuevo usuario
                              return userRepository.save(myUserCreated)
                                      .flatMap(user -> {
                                        return roleRepository.findById(2L)
                                                    .flatMap(
                                                      role -> {
                                                          return userRoleRepository.saveUserRole(user.getId(), role.getId())
                                                                    .then(Mono.just("User created with roles: " + user.getUsername()));
                                                      }
                                                    );
                                      });
                                      
                                      
        });
    }      

    
}
