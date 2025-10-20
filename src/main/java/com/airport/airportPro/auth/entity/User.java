package com.airport.airportPro.auth.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    @Column("id")
    private Long id;
    @Column("username")
    private String username;
    @Column("password")
    private String password;
    @Column("email")
    private String email;
    
 
}
