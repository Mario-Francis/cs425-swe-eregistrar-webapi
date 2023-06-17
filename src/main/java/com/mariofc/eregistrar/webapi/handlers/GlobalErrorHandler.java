package com.mariofc.eregistrar.webapi.handlers;

import com.mariofc.eregistrar.webapi.dtos.ApiResponse;
import com.mariofc.eregistrar.webapi.dtos.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(e-> new ValidationError(e.getField(), e.getDefaultMessage())).toList();
        var apiResponse = new ApiResponse<>("One or more fields failed validation.");
        apiResponse.setErrors(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
