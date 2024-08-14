package com.spring.jwt.implementation.jwt.service.impl;

import com.spring.jwt.implementation.jwt.dto.UserJwtDTO;
import com.spring.jwt.implementation.jwt.entity.UserJwtEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MappingObjects {

    // DTO -> DAO mapping (used for postMapping)
    public UserJwtEntity objectMappingAllDtoToDao(UserJwtDTO userJwtDTO){
        UserJwtEntity userJwtEntity =  UserJwtEntity.builder()
                .id(userJwtDTO.getId())
                .name(userJwtDTO.getName())
                .email(userJwtDTO.getEmail())
                .mobileNumber(userJwtDTO.getMobileNumber())
                .dateOfBirth(userJwtDTO.getDateOfBirth()).build();
        log.info("Mapped All the DTO to DAO objects successfully!");
        return userJwtEntity;
    }

    // DAO -> DTO mapping (used for getMapping)
    public UserJwtDTO objectMappingAllDaoToDto(UserJwtEntity userJwtEntity){
        UserJwtDTO userJwtDTO = UserJwtDTO.builder()
                .id(userJwtEntity.getId())
                .name(userJwtEntity.getName())
                .email(userJwtEntity.getEmail())
                .mobileNumber(userJwtEntity.getMobileNumber())
                .dateOfBirth(userJwtEntity.getDateOfBirth()).build();
        log.info("Mapped the DAO to DTO objects successfully!");
        return userJwtDTO;
    }

    public UserJwtEntity objectMappingDtoToDao(UserJwtDTO userJwtDTO){
        UserJwtEntity userJwtEntity =  UserJwtEntity.builder()
                .name(userJwtDTO.getName())
                .email(userJwtDTO.getEmail())
                .mobileNumber(userJwtDTO.getMobileNumber())
                .dateOfBirth(userJwtDTO.getDateOfBirth()).build();
        log.info("Mapped the DTO to DAO objects successfully!");
        return userJwtEntity;
    }




}
