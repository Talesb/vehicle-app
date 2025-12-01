package com.ctw.service;

import com.ctw.entity.Engine;
import com.ctw.dto.EngineDTO;
import com.ctw.repository.EngineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EngineService {
    @Inject
    EngineRepository engineRepository;

    @Transactional
    public List<EngineDTO> getAll() {
        return engineRepository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public EngineDTO getById(String id) {
        Engine engine = engineRepository.findById(id);
        return engine != null ? toDTO(engine) : null;
    }

    @Transactional
    public EngineDTO create(EngineDTO dto) {
        Engine engine = new Engine();
        engine.setType(Enum.valueOf(com.ctw.entity.EngineType.class, dto.getType()));
        engine.setHorsepower(dto.getHorsepower());
        engine.setName(dto.getName());
        engineRepository.persist(engine);
        return toDTO(engine);
    }

    @Transactional
    public EngineDTO update(String id, EngineDTO dto) {
        Engine engine = engineRepository.findById(id);
        if (engine == null) return null;
        engine.setType(Enum.valueOf(com.ctw.entity.EngineType.class, dto.getType()));
        engine.setHorsepower(dto.getHorsepower());
        engine.setName(dto.getName());
        return toDTO(engine);
    }

    @Transactional
    public boolean delete(String id) {
        return engineRepository.deleteById(id);
    }

    private EngineDTO toDTO(Engine engine) {
        EngineDTO dto = new EngineDTO();
        dto.setId(engine.getId());
        dto.setType(engine.getType().name());
        dto.setHorsepower(engine.getHorsepower());
        dto.setName(engine.getName());
        return dto;
    }
}
