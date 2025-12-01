package com.ctw.repository;

import com.ctw.entity.Vehicle;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehicleRepository implements PanacheRepositoryBase<Vehicle, String> {
}
