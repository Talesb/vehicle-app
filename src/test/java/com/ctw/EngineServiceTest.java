package com.ctw;

import com.ctw.dto.EngineDTO;
import com.ctw.entity.Engine;
import com.ctw.entity.EngineType;
import com.ctw.repository.EngineRepository;
import com.ctw.service.EngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EngineServiceTest {
    @Mock
    EngineRepository engineRepository;

    @InjectMocks
    EngineService engineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEngine() {
        EngineDTO dto = new EngineDTO();
        dto.setType("ELECTRIC");
        dto.setHorsepower(100);
        dto.setName("Test Engine");
        Engine engine = new Engine();
        engine.setType(EngineType.ELECTRIC);
        engine.setHorsepower(100);
        engine.setName("Test Engine");
        EngineDTO result = engineService.create(dto);
        assertEquals("ELECTRIC", result.getType());
        assertEquals(100, result.getHorsepower());
        assertEquals("Test Engine", result.getName());
    }

    @Test
    void testGetAllEngines() {
        Engine engine1 = new Engine();
        engine1.setType(EngineType.ELECTRIC);
        engine1.setHorsepower(100);
        engine1.setName("Engine1");
        Engine engine2 = new Engine();
        engine2.setType(EngineType.GASOLINE);
        engine2.setHorsepower(200);
        engine2.setName("Engine2");
        when(engineRepository.listAll()).thenReturn(Arrays.asList(engine1, engine2));
        List<EngineDTO> result = engineService.getAll();
        assertEquals(2, result.size());
        assertEquals("Engine1", result.get(0).getName());
        assertEquals("Engine2", result.get(1).getName());
    }

    @Test
    void testGetById() {
        Engine engine = new Engine();
        engine.setId("123");
        engine.setType(EngineType.DIESEL);
        engine.setHorsepower(300);
        engine.setName("Diesel Engine");
        when(engineRepository.findById("123")).thenReturn(engine);
        EngineDTO result = engineService.getById("123");
        assertNotNull(result);
        assertEquals("Diesel Engine", result.getName());
        assertEquals("DIESEL", result.getType());
    }

    @Test
    void testUpdateEngine() {
        Engine engine = new Engine();
        engine.setId("123");
        engine.setType(EngineType.GASOLINE);
        engine.setHorsepower(150);
        engine.setName("Old Name");
        when(engineRepository.findById("123")).thenReturn(engine);
        EngineDTO dto = new EngineDTO();
        dto.setType("ELECTRIC");
        dto.setHorsepower(200);
        dto.setName("New Name");
        EngineDTO result = engineService.update("123", dto);
        assertEquals("ELECTRIC", result.getType());
        assertEquals(200, result.getHorsepower());
        assertEquals("New Name", result.getName());
    }

    @Test
    void testDeleteEngine() {
        when(engineRepository.deleteById("123")).thenReturn(true);
        boolean result = engineService.delete("123");
        assertTrue(result);
    }
}

