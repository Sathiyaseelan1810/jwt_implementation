package com.spring.jwt.implementation.jwt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // used to create the builder class(design pattern)
public class UserJwtDTO {

    private Integer id;

    @NotBlank(message = "User Name should not be blank/null")
    private String name;

    @NotBlank(message = "User Email should not be blank/null")
    private String email;

    @NotBlank(message = "User MobileNumber should not be blank/null")
    private String mobileNumber;

    @NotBlank(message = "User Date Of Birth should not be blank/null")
    private String dateOfBirth;


}
