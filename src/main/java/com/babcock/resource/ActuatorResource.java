package com.babcock.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorResource {
    @RequestMapping(value = "/actuator", method = RequestMethod.GET)
    public String helloActuator() {
        return "Hello Spring Boot Actuator";
    }
}
