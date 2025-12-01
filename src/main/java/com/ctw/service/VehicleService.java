package com.ctw.service;

import com.ctw.entity.Vehicle;
import com.ctw.entity.Engine;
import com.ctw.dto.VehicleDTO;
import com.ctw.repository.VehicleRepository;
import com.ctw.repository.EngineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VehicleService {
    @Inject
    VehicleRepository vehicleRepository;
    @Inject
    EngineRepository engineRepository;

    public List<VehicleDTO> getAll() {
        return vehicleRepository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public VehicleDTO getById(String id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        return vehicle != null ? toDTO(vehicle) : null;
    }

    public VehicleDTO create(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(dto.getName());
        vehicle.setPrice(dto.getPrice());
        vehicle.setColor(dto.getColor());
        Engine engine = engineRepository.findById(dto.getEngineId());
        vehicle.setEngine(engine);
        vehicleRepository.persist(vehicle);
        return toDTO(vehicle);
    }

    public VehicleDTO update(String id, VehicleDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) return null;
        vehicle.setName(dto.getName());
        vehicle.setPrice(dto.getPrice());
        vehicle.setColor(dto.getColor());
        Engine engine = engineRepository.findById(dto.getEngineId());
        vehicle.setEngine(engine);
        return toDTO(vehicle);
    }

    public boolean delete(String id) {
        return vehicleRepository.deleteById(id);
    }

    private VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setPrice(vehicle.getPrice());
        dto.setColor(vehicle.getColor());
        dto.setEngineId(vehicle.getEngine() != null ? vehicle.getEngine().getId() : null);
        return dto;
    }
}
