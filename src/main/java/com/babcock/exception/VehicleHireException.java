package com.babcock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class VehicleHireException extends RuntimeException {
    public VehicleHireException() {
        super();
    }

    public VehicleHireException(String s) {
        super(s);
    }

    public VehicleHireException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VehicleHireException(Throwable throwable) {
        super(throwable);
    }
}
