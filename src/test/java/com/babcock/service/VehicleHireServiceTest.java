package com.babcock.service;

import com.babcock.model.Vehicle;
import com.babcock.repository.VehicleHireRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleHireServiceTest {

    @Mock
    private VehicleHireRepository vehicleHireRepository;

    @InjectMocks
    private VehicleHireService vehicleHireService;

    @Test
    void testAvailableVehiclesToHire() {
        when(vehicleHireRepository.availableVehiclesToHire(LocalDate.now())).thenReturn(createVehiclesToHire());
        Optional<List<Vehicle>> listVehicles = vehicleHireService.availableVehiclesToHire(LocalDate.now());
        List<Vehicle> filteredList = listVehicles.stream().flatMap(List<Vehicle>::stream).collect(Collectors.toList());
        assertTrue(listVehicles.isPresent());
        assertEquals(2, filteredList.size());
        assertEquals("REG2", filteredList.get(0).getRegistrationNumber());
        assertEquals("REG3", filteredList.get(1).getRegistrationNumber());
    }

    @Test
    void testGetVehicleByRegistrationNumber() {
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(Vehicle.builder().registrationNumber("REG1").category("Estate car").make("Ford").
                model("Focus").fuelType("Petrol").customer("Sam").pricePerDay(new BigDecimal(35)).hireStartDate(LocalDate.parse("2020-09-01"))
                .hireEndDate(LocalDate.parse("2020-09-07")).build());
        when(vehicleHireRepository.findByRegistrationNumber("REG1")).thenReturn(optionalVehicle);
        Optional<Vehicle> vehicles = vehicleHireService.getVehicleByRegistrationNumber("REG1");
        assertEquals("Ford", vehicles.get().getMake());
        assertEquals("Focus", vehicles.get().getModel());
        assertEquals("REG1", vehicles.get().getRegistrationNumber());
    }

    private Optional<List<Vehicle>> createVehiclesToHire() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = Vehicle.builder().id(2L).registrationNumber("REG2").category("Small car").make("Mercedes").
                model("C class").fuelType("Petrol").customer("John").pricePerDay(new BigDecimal(25)).hireStartDate(null)
                .hireEndDate(null).build();
        Vehicle vehicle2 = Vehicle.builder().id(3L).registrationNumber("REG3").category("Van").make("VW").
                model("Transporter").fuelType("Diesel").customer("Doe").pricePerDay(new BigDecimal(50)).hireStartDate(null)
                .hireEndDate(null).build();

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        return Optional.ofNullable(vehicles);
    }

}