package com.airport.airportPro.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("Token")
public class Token {

    @Id
    private Long id;
    private String token;
    private Long user_id;
    private String token_type;
    private boolean expired;
    private boolean revoked; 

}
