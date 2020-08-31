package com.babcock.resource;

import com.babcock.exception.VehicleNotFoundException;
import com.babcock.model.Vehicle;
import com.babcock.resource.model.VehicleResponse;
import com.babcock.service.VehicleHireService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@RestController
@RequestMapping("/vehicles")
public class VehicleHireResource {

    @Autowired
    private VehicleHireService vehicleHireService;

    public VehicleHireResource(VehicleHireService vehicleHireService) {
        this.vehicleHireService = vehicleHireService;
    }

    @GetMapping("/hire")
    public ResponseEntity<List<Vehicle>> availableVehiclesToHire(@RequestParam(value = "date")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate date) {
        return vehicleHireService.availableVehiclesToHire(date).
                map(vehicles -> new ResponseEntity<>(vehicles, new HttpHeaders(), HttpStatus.OK)).orElseThrow(() ->
                new VehicleNotFoundException("Can't find vehicle for the date - " + date));
    }

    @GetMapping("/{registrationNumber}/{startDate}/{endDate}")
    public ResponseEntity<VehicleResponse> calculateVehicleCostToHire(
            @PathVariable("registrationNumber") @NotNull String registrationNumber,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull @PathVariable("startDate") String startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull @PathVariable("endDate") String endDate) {

        return vehicleHireService.getVehicleByRegistrationNumber(registrationNumber).map(vehicle -> {
            Period period = Period.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
            int noOfDays = period.getDays();
            BigDecimal vehicleHireCost = vehicle.getPricePerDay().multiply(new BigDecimal(noOfDays));

            VehicleResponse vehicleResponse = VehicleResponse
                    .builder()
                    .vehicleHireCost(vehicleHireCost)
                    .statusCode(HttpStatus.OK.value())
                    .build();
            return new ResponseEntity<>(vehicleResponse, new HttpHeaders(), HttpStatus.OK);
        }).
                orElseThrow(() -> new VehicleNotFoundException("Vehicle with Id - " + registrationNumber + " not found"));

    }

}
