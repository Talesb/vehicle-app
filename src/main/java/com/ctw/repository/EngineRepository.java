package com.ctw.repository;

import com.ctw.entity.Engine;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EngineRepository implements PanacheRepositoryBase<Engine, String> {
}
