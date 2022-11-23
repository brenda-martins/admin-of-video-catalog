package com.admin.catalog.infrastructure.api.controllers;

import com.admin.catalog.domain.exceptions.DomainException;
import com.admin.catalog.domain.validation.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException domainException){
        return ResponseEntity.unprocessableEntity().body(ApiError.from(domainException));
    }

    record ApiError(String message, List<Error> errors){
        static ApiError from(final DomainException domainException){
            return new ApiError(domainException.getMessage(), domainException.getErrors());
        }
    }
}
