package com.babcock.repository;

import com.babcock.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class VehicleHireRepositoryTest {

    @Autowired
    private VehicleHireRepository vehicleHireRepository;

    @Test
    void testFindByRegistrationNumber() {
        Optional<Vehicle> foundVehicle = vehicleHireRepository.findByRegistrationNumber("REG1");
        assertEquals("Ford", foundVehicle.get().getMake());
        assertEquals("Focus", foundVehicle.get().getModel());
    }

    @Test
    void testAvailableVehiclesToHire() {
        Optional<List<Vehicle>> optionalVehicles = vehicleHireRepository.availableVehiclesToHire(LocalDate.now());
        List<Vehicle> filteredList = optionalVehicles.stream().flatMap(List<Vehicle>::stream).collect(Collectors.toList());
        assertTrue(optionalVehicles.isPresent());
        assertEquals(2, filteredList.size());
    }
}