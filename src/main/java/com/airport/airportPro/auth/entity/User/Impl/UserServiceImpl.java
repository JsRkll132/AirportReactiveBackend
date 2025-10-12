package com.airport.airportPro.auth.entity.User.Impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airport.airportPro.auth.entity.Role.RoleRepository;
import com.airport.airportPro.auth.entity.User.MyUserDetails;
import com.airport.airportPro.auth.entity.User.UserRepository;
import com.airport.airportPro.auth.entity.User.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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
}
