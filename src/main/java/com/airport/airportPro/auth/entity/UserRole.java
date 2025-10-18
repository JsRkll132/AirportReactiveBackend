package com.airport.airportPro.auth.entity;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("user_role")
public class UserRole {
    
    @Id
   
    @Column("user_id")
    private Long user_id;
    @Column("role_id")
    private Long role_id; 
 
}
