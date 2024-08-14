package com.spring.jwt.implementation.jwt.controller;

import com.spring.jwt.implementation.jwt.dto.UserJwtDTO;
import com.spring.jwt.implementation.jwt.exception.UserJwtException;
import com.spring.jwt.implementation.jwt.service.UserJwtRequestService;
import com.spring.jwt.implementation.jwt.service.impl.UserJwtImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/jwt_validation")
public class UserJwtController {

    @Autowired
    private UserJwtRequestService userJwtRequestService;

    @Autowired
    private UserJwtImpl userJwt; // For storing the records when the db is initialized

    @PostMapping("/user")
    public ResponseEntity<String> postUser(@Valid @RequestBody UserJwtDTO userJwtDTO) {
        this.userJwtRequestService.postUser(userJwtDTO);
        log.info("Single User is created through Post Mapping!");
        return new ResponseEntity<>("User is created successfully/registered in the DB", HttpStatus.CREATED);
    }

    // Request body to provide a value from the user::
    @PostMapping("/allUsers")
    public ResponseEntity<List<UserJwtDTO>> postAllUsers(@Valid @RequestBody List<UserJwtDTO> userJwtDTOList) {
        this.userJwtRequestService.postAllUsers(userJwtDTOList);
        log.info("Multiple Users are created through Post Mapping!");
        return new ResponseEntity<>(userJwtDTOList, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public ResponseEntity<UserJwtDTO> getUserByID(@PathVariable Integer user_id) throws UserJwtException {
        log.info("Fetched the matched user through Get Mapping!");
        return new ResponseEntity<>(this.userJwtRequestService.getUserById(user_id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserJwtDTO>> getAllUsers() {
        log.info("Fetched all the users through Get Mapping!");
        return new ResponseEntity<>(this.userJwtRequestService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserJwtDTO userJwtDTO) {
        this.userJwtRequestService.updateUser(userJwtDTO);
        log.info("Update made to the matched user is completed through Put Mapping!");
        return new ResponseEntity<>("User is updated successfully/registered in the DB", HttpStatus.OK);
    }


    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer user_id) {
        this.userJwtRequestService.deleteUserById(user_id);
        log.info("User is deleted successfully from the db!");
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.NO_CONTENT);
    }



}
