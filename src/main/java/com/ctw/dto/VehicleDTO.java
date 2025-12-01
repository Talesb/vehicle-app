package com.ctw.dto;

public class VehicleDTO {
    private String id;
    private String name;
    private double price;
    private String color;
    private String engineId;

    public VehicleDTO() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getEngineId() { return engineId; }
    public void setEngineId(String engineId) { this.engineId = engineId; }
}
