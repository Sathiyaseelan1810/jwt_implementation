package com.spring.jwt.implementation.jwt.service;

import com.spring.jwt.implementation.jwt.dto.UserJwtDTO;
import com.spring.jwt.implementation.jwt.exception.UserJwtException;

import java.util.List;

public interface UserJwtRequestService {

    void postUser(UserJwtDTO userJwtDTO);

    void postAllUsers(List<UserJwtDTO> userJwtDTOList);

    UserJwtDTO getUserById(Integer user_id) throws UserJwtException;

    List<UserJwtDTO> getAllUsers();

    void updateUser(UserJwtDTO userJwtDTO);

    void deleteUserById(Integer user_id);
}
