package com.babcock.resource;

import com.babcock.model.Vehicle;
import com.babcock.service.VehicleHireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehicleHireResourceTest {

    private static final String JSON_RESPONSE_CALCULATE_VEHICLE_COST = "{\"statusCode\":200,\"vehicleHireCost\":210}";

    private static final String JSON_RESPONSE_GET_AVAILABLE_VEHICLES_TO_HIRE =
            "[{\"id\":2,\"registrationNumber\":\"REG2\",\"category\":\"Small car\",\"make\":\"Mercedes\","
                    + "\"model\":\"C class\",\"fuelType\":\"Petrol\",\"customer\":\"John\",\"pricePerDay\":25,"
                    + "\"hireStartDate\":null,\"hireEndDate\":null},"
                    + "{\"id\":3,\"registrationNumber\":\"REG3\",\"category\":\"Van\",\"make\":\"VW\","
                    + "\"model\":\"Transporter\",\"fuelType\":\"Diesel\",\"customer\":\"Doe\",\"pricePerDay\":50,"
                    + "\"hireStartDate\":null,\"hireEndDate\":null}]";


    private MockMvc mockMvc;

    @InjectMocks
    private VehicleHireResource vehicleHireResource;

    @Mock
    private VehicleHireService vehicleHireService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.vehicleHireResource).build();
    }

    @Test
    void testAvailableVehiclesToHire() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDate = LocalDate.now().format(formatter);

        when(vehicleHireService.availableVehiclesToHire(LocalDate.now())).thenReturn(createVehiclesToHire());
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/hire")
                .param("date", formatDate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_GET_AVAILABLE_VEHICLES_TO_HIRE));

    }

    @Test
    void testCalculateVehicleCostToHire() throws Exception {
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(Vehicle.builder().registrationNumber("REG1").category("Estate car").make("Ford").
                model("Focus").fuelType("Petrol").customer("Sam").pricePerDay(new BigDecimal(35)).hireStartDate(LocalDate.parse("2020-09-01"))
                .hireEndDate(LocalDate.parse("2020-09-07")).build());
        when(vehicleHireService.getVehicleByRegistrationNumber("REG1")).thenReturn(optionalVehicle);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/REG1/2020-09-01/2020-09-07"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(JSON_RESPONSE_CALCULATE_VEHICLE_COST));
    }

    @Test
    void testAvailableVehiclesToHireNotFoundException() throws Exception {
        mockMvc.perform(get("/vehicles/X")).andExpect(status().isNotFound());
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