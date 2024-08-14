package com.spring.jwt.implementation.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_authentication")
public class AuthenticateEntity {

    @Id // Primary Key::
    private int id;
    private String userName;
    private String passWord;
    private String email;

}
