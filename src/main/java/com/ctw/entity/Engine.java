package com.ctw.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Engine {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private EngineType type;

    private int horsepower;

    public Engine() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}

