package com.spring.jwt.implementation.jwt.repository;

import com.spring.jwt.implementation.jwt.entity.AuthenticateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

  public interface AuthenticateJwtRepo extends JpaRepository<AuthenticateEntity, Integer> {

}
