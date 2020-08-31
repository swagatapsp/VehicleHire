package com.babcock.service;

import com.babcock.model.Vehicle;
import com.babcock.repository.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleHireService {

    @Autowired
    private VehicleHireRepository vehicleHireRepository;


    public Optional<List<Vehicle>> availableVehiclesToHire(LocalDate date) {
        return vehicleHireRepository.availableVehiclesToHire(date);
    }


    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleHireRepository.findByRegistrationNumber(registrationNumber);

    }

}
