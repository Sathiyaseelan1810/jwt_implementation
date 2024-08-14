package com.spring.jwt.implementation.jwt.repository;

import com.spring.jwt.implementation.jwt.entity.UserJwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJwtRepository extends JpaRepository<UserJwtEntity, Long> {
}
