package com.babcock.exception;

import com.babcock.model.APIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {VehicleHireException.class})
    public APIError handleVehicleHireException(VehicleHireException ex) {
        log.error(ex.getMessage());
        return APIError.builder().exception(ex.getMessage()).message("SSL handshake error happened").code(500).build();
    }

    @ExceptionHandler(value = {VehicleNotFoundException.class})
    public APIError handleVehicleNotFoundException(VehicleNotFoundException ex) {
        log.error(ex.getMessage());
        return APIError.builder().exception(ex.getMessage()).message("SSL handshake error happened").code(400).build();
    }
}
