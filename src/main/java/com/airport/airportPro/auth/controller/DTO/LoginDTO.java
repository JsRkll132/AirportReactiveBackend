package com.airport.airportPro.auth.controller.DTO;


public record LoginDTO(String username
                //can be username or email 
                ,String password) {

}
