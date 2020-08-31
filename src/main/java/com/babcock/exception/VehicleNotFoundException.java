package com.babcock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException() {
        super();
    }

    public VehicleNotFoundException(String s) {
        super(s);
    }

    public VehicleNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VehicleNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
