package com.spring.jwt.implementation.jwt.service.impl;

import com.spring.jwt.implementation.jwt.dto.UserJwtDTO;
import com.spring.jwt.implementation.jwt.entity.UserJwtEntity;
import com.spring.jwt.implementation.jwt.exception.UserJwtException;
import com.spring.jwt.implementation.jwt.repository.UserJwtRepository;
import com.spring.jwt.implementation.jwt.service.UserJwtRequestService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
@EnableAsync
public class UserJwtImpl extends MappingObjects implements UserJwtRequestService{


    @Autowired
    private UserJwtRepository userJwtRepository;

    @PostConstruct
    public void initUsers()
    {
        List<UserJwtEntity> userJwtEntityList = Stream.of(
                new UserJwtEntity(100,"Sathiya0", "pwd0@gmail.com", "9988776655", "10-10-2000"),
                new UserJwtEntity(101,"Sathiya1", "pwd1@gmail.com", "0088776655", "10-10-2001"),
                new UserJwtEntity(102,"Sathiya2", "pwd2@gmail.com", "8988776655", "10-10-2002"),
                new UserJwtEntity(103,"Sathiya3", "pwd3@gmail.com", "7988776655", "10-10-2003"),
                new UserJwtEntity(104,"Sathiya4", "pwd4@gmail.com", "6988776655", "10-10-2004"),
                new UserJwtEntity(105,"Sathiya5", "pwd5@gmail.com", "5988776655", "10-10-2005"),
                new UserJwtEntity(106,"Sathiya6", "pwd6@gmail.com", "4988776655", "10-10-2006")
                ).collect(Collectors.toList());
        this.userJwtRepository.saveAll(userJwtEntityList);
    }

        // Note:: No need to append the user_id for the postRequest
        @Override
        public void postUser(UserJwtDTO userJwtDTO) {
            this.userJwtRepository.save(objectMappingDtoToDao(userJwtDTO));
            log.info("Successfully Posted the New user to the DB!");
        }

        @Override
        public void postAllUsers(List<UserJwtDTO> userJwtDTOList) {
            List<UserJwtEntity> userJwtEntityList = userJwtDTOList
                    .stream()
                    .map(this::objectMappingDtoToDao)
                    .collect(Collectors.toList());
            this.userJwtRepository.saveAll(userJwtEntityList);
            log.info("Successfully Posted the multiple New users to the DB!");
            /*
            use the save function use the below code...
                        for(UserJwtDTO userJwtDTO: userJwtDTOList)
                            this.userJwtRepository.save(objectMappingDtoToDao(userJwtDTO));
            */
        }

        // Step1: User need to find based on the id
        // Step2: Write the exception if the user not found in the db
        // Step3: If found, then get the value in the db
        // Step4: Map the returned entity Object to dto object.
        @Override
        public UserJwtDTO getUserById(Integer user_id) throws UserJwtException {
           Optional<UserJwtEntity> userJwtEntityOptional = this.userJwtRepository.findById(Long.valueOf(user_id));
           if(userJwtEntityOptional.isEmpty())
               throw new UserJwtException("User with ID : " + user_id + "is not found in the db!");
           UserJwtEntity userJwtEntity = userJwtEntityOptional.get();
           log.info("Successfully retrieved the matching user from db based on their id");
           return objectMappingAllDaoToDto(userJwtEntity);
        }


        @Override
        public List<UserJwtDTO> getAllUsers() {
            List<UserJwtEntity> userJwtEntityList = (List<UserJwtEntity>) this.userJwtRepository.findAll();
            log.info("Successfully Retrieved all the users from the db!");
            return userJwtEntityList
                    .stream()
                    //.map(users -> objectMappingDaoToDto(users))
                    .map(this::objectMappingAllDaoToDto)
                    .collect(Collectors.toList());
        }

        // Note:: Need to append the user_id to make the update
        //@Async
        @Override
        public void updateUser(UserJwtDTO userJwtDTO) {
            this.userJwtRepository.save(objectMappingAllDtoToDao(userJwtDTO));
            log.info("Update to the matching user is completed successfully!");
        }

        @Override
        public void deleteUserById(Integer user_id) {
            this.userJwtRepository.deleteById(Long.valueOf(user_id));
            log.info("User with id : " + user_id + " is deleted from the DB");
        }
}

