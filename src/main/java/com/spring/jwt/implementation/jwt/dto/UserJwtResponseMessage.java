package com.spring.jwt.implementation.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJwtResponseMessage {

    private String developerMessage;
    private String errorMessage;
}
