package com.babcock.repository;

import com.babcock.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleHireRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber = :registrationNumber")
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT v FROM Vehicle v WHERE :date BETWEEN v.hireStartDate and v.hireEndDate")
    Optional<List<Vehicle>> availableVehiclesToHire(@Param("date") LocalDate date);
}