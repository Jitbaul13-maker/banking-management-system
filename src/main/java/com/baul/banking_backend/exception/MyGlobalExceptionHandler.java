package com.baul.banking_backend.exception;

import com.baul.banking_backend.DTOs.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<ErrorResponseDTO> ResourceNotfoundException(ResourceNotfoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body( new ErrorResponseDTO
                        (LocalDateTime.now(),
                        404,
                        "Bad Request",
                        ex.getMessage())
                );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> BadCredentialsException(InvalidCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body( new ErrorResponseDTO
                        (LocalDateTime.now(),
                                401,
                                "Unauthorized",
                                ex.getMessage())
                );
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponseDTO> InsufficientBalanceException(InsufficientBalanceException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body( new ErrorResponseDTO
                        (LocalDateTime.now(),
                                409,
                                "Not Found",
                                ex.getMessage())
                );
    }

    @ExceptionHandler(InactiveAccountException.class)
    public ResponseEntity<ErrorResponseDTO> InactiveAccountException(InactiveAccountException ex){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponseDTO(
                        LocalDateTime.now(),
                        403,
                        "Forbidden",
                        ex.getMessage()
                ));
    }
}
