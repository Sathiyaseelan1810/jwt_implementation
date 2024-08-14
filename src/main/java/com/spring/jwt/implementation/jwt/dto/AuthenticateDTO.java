package com.spring.jwt.implementation.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // used to create the builder class(design pattern)
public class AuthenticateDTO {
    private String username;
    private String password;


}
