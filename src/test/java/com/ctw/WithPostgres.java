package com.ctw;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;



public class WithPostgres implements QuarkusTestResourceLifecycleManager {

    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").withDatabaseName("testdb").withUsername("test").withPassword("test");

    @Override
    public java.util.Map<String, String> start() {
        postgres.start();
        java.util.Map<String, String> props = new java.util.HashMap<>();
        props.put("quarkus.datasource.jdbc.url", postgres.getJdbcUrl());
        props.put("quarkus.datasource.username", postgres.getUsername());
        props.put("quarkus.datasource.password", postgres.getPassword());
        props.put("quarkus.datasource.db-kind", "postgresql");
        props.put("quarkus.hibernate-orm.database.generation", "drop-and-create");
        return props;
    }

    @Override
    public void stop() {
        postgres.stop();
    }
}
