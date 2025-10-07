package com.meditrack.doctor_service.exception;

import com.meditrack.doctor_service.exception.DoctorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDoctorNotFound(DoctorNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGeneralErrors(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Unexpected error: "+ex.getMessage()));
    }
}
