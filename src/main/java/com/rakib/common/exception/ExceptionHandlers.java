package com.rakib.common.exception;

import com.rakib.common.exception.classes.AlredayExistException;
import com.rakib.common.exception.classes.InvalidKeyException;
import com.rakib.common.exception.classes.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlredayExistException.class)
    public ResponseEntity<Object> handleException(AlredayExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("AlreadyExist")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("NotFound")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()));
    }

    @ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<Object> handleException(InvalidKeyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("InvalidKey")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()));
    }
}
