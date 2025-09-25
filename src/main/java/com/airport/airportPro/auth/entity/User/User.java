package com.airport.airportPro.auth.entity.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
}
