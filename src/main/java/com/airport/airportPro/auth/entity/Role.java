package com.airport.airportPro.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("role")
public class Role {
    @Id
    private Long id;
    private String name;
}
