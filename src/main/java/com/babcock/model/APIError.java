package com.babcock.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class APIError {
    private int code;
    private String exception;
    private String message;
}
