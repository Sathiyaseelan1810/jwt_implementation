package com.spring.jwt.implementation.jwt.handler;

import com.spring.jwt.implementation.jwt.dto.UserJwtResponseMessage;
import com.spring.jwt.implementation.jwt.exception.UserJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class UserJwtHandler {

    //@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> sQLIntegrityConstraintViolationException(RuntimeException runtimeException)
    {
        return new ResponseEntity<String>(runtimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldError>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        List <FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        return new ResponseEntity<List<FieldError>>(fieldErrorList, HttpStatus.BAD_REQUEST);
    }

    // Customized Exception based on the Micro Services:
    @ExceptionHandler(UserJwtException.class)
    public ResponseEntity<UserJwtResponseMessage> userRegistrationException(UserJwtException registrationException)
    {
        UserJwtResponseMessage userRegistrationResonseMessage = UserJwtResponseMessage.builder().developerMessage(registrationException.getMessage()).build();
        return new ResponseEntity<UserJwtResponseMessage>(userRegistrationResonseMessage, HttpStatus.OK);
    }
}
