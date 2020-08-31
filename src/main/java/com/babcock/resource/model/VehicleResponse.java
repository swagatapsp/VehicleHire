package com.babcock.resource.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class VehicleResponse {

    private int statusCode;
    private BigDecimal vehicleHireCost;

}
