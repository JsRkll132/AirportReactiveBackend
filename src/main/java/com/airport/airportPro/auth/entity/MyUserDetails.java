package com.airport.airportPro.auth.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
public class MyUserDetails  extends User{
    private String email;
    public MyUserDetails(
            String username, String password , String email,
            boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
 
}
