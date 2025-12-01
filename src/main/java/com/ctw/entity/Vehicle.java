package com.ctw.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Vehicle {
    @Id
    private String id;
    private String name;
    private double price;
    private String color;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public Vehicle() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

