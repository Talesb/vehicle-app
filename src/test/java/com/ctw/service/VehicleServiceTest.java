package com.ctw.service;

import com.ctw.dto.VehicleDTO;
import com.ctw.entity.Engine;
import com.ctw.entity.EngineType;
import com.ctw.entity.Vehicle;
import com.ctw.repository.EngineRepository;
import com.ctw.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {
    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    EngineRepository engineRepository;
    @InjectMocks
    VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() {
        VehicleDTO dto = new VehicleDTO();
        dto.setName("Car");
        dto.setPrice(25000.0);
        dto.setColor("Red");
        dto.setEngineId("engine-1");
        Engine engine = new Engine();
        engine.setId("engine-1");
        engine.setType(EngineType.ELECTRIC);
        when(engineRepository.findById("engine-1")).thenReturn(engine);
        VehicleDTO result = vehicleService.create(dto);
        assertEquals("Car", result.getName());
        assertEquals(25000.0, result.getPrice());
        assertEquals("Red", result.getColor());
        assertEquals("engine-1", result.getEngineId());
    }

    @Test
    void testGetAllVehicles() {
        Engine engine = new Engine();
        engine.setId("engine-1");
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId("v1");
        vehicle1.setName("Car1");
        vehicle1.setPrice(10000.0);
        vehicle1.setColor("Blue");
        vehicle1.setEngine(engine);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId("v2");
        vehicle2.setName("Car2");
        vehicle2.setPrice(20000.0);
        vehicle2.setColor("Green");
        vehicle2.setEngine(engine);
        when(vehicleRepository.listAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));
        List<VehicleDTO> result = vehicleService.getAll();
        assertEquals(2, result.size());
        assertEquals("Car1", result.get(0).getName());
        assertEquals("Car2", result.get(1).getName());
    }

    @Test
    void testGetById() {
        Engine engine = new Engine();
        engine.setId("engine-2");
        Vehicle vehicle = new Vehicle();
        vehicle.setId("v1");
        vehicle.setName("Car");
        vehicle.setPrice(15000.0);
        vehicle.setColor("Yellow");
        vehicle.setEngine(engine);
        when(vehicleRepository.findById("v1")).thenReturn(vehicle);
        VehicleDTO result = vehicleService.getById("v1");
        assertNotNull(result);
        assertEquals("Car", result.getName());
        assertEquals("Yellow", result.getColor());
        assertEquals("engine-1", result.getEngineId());
    }

    @Test
    void testUpdateVehicle() {
        Engine engine = new Engine();
        engine.setId("engine-2");
        Vehicle vehicle = new Vehicle();
        vehicle.setId("v1");
        vehicle.setName("OldCar");
        vehicle.setPrice(12000.0);
        vehicle.setColor("Black");
        vehicle.setEngine(engine);
        when(vehicleRepository.findById("v1")).thenReturn(vehicle);
        Engine newEngine = new Engine();
        newEngine.setId("engine-3");
        when(engineRepository.findById("engine-3")).thenReturn(newEngine);
        VehicleDTO dto = new VehicleDTO();
        dto.setName("NewCar");
        dto.setPrice(18000.0);
        dto.setColor("White");
        dto.setEngineId("engine-3");
        VehicleDTO result = vehicleService.update("v1", dto);
        assertEquals("NewCar", result.getName());
        assertEquals(18000.0, result.getPrice());
        assertEquals("White", result.getColor());
        assertEquals("engine-3", result.getEngineId());
    }

    @Test
    void testDeleteVehicle() {
        when(vehicleRepository.deleteById("v1")).thenReturn(true);
        boolean result = vehicleService.delete("v1");
        assertTrue(result);
    }
}

