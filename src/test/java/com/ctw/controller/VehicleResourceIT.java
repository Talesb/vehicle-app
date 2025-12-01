package com.ctw.controller;

import com.ctw.WithPostgres;
import com.ctw.dto.EngineDTO;
import com.ctw.dto.VehicleDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusIntegrationTest
@QuarkusTestResource(WithPostgres.class)
class VehicleResourceIT {

    @Test
    void testCreateAndGetVehicle() {
        // First, create an engine
        EngineDTO engine = new EngineDTO();
        engine.setType("ELECTRIC");
        engine.setHorsepower(150);
        engine.setName("VehicleEngine");
        String engineId =
                given().contentType(ContentType.JSON)
                        .body(engine).when().post("/engines").then().statusCode(201).extract().path("id");

        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setName("TestCar");
        vehicle.setPrice(30000.0);
        vehicle.setColor("Blue");
        vehicle.setEngineId(engineId);

        String vehicleId = given().contentType(ContentType.JSON).body(vehicle).when().post("/vehicles").then().statusCode(201).body("name", equalTo("TestCar")).body("price", equalTo(30000.0f)).body("color", equalTo("Blue")).body("engineId", equalTo(engineId)).extract().path("id");

        given().when().get("/vehicles/" + vehicleId).then().statusCode(200).body("id", equalTo(vehicleId)).body("name", equalTo("TestCar")).body("price", equalTo(30000.0f)).body("color", equalTo("Blue")).body("engineId", equalTo(engineId));
    }

    @Test
    void testUpdateAndDeleteVehicle() {
        // Create engine
        EngineDTO engine = new EngineDTO();
        engine.setType("GASOLINE");
        engine.setHorsepower(180);
        engine.setName("UpdateEngine");
        String engineId = given().contentType(ContentType.JSON).body(engine).when().post("/engines").then().statusCode(201).extract().path("id");

        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setName("OldCar");
        vehicle.setPrice(20000.0);
        vehicle.setColor("Red");
        vehicle.setEngineId(engineId);
        String vehicleId = given().contentType(ContentType.JSON).body(vehicle).when().post("/vehicles").then().statusCode(201).extract().path("id");

        VehicleDTO update = new VehicleDTO();
        update.setName("NewCar");
        update.setPrice(25000.0);
        update.setColor("Green");
        update.setEngineId(engineId);

        given().contentType(ContentType.JSON).body(update).when().put("/vehicles/" + vehicleId).then().statusCode(200).body("name", equalTo("NewCar")).body("price", equalTo(25000.0f)).body("color", equalTo("Green")).body("engineId", equalTo(engineId));

        given().when().delete("/vehicles/" + vehicleId).then().statusCode(204);

        given().when().get("/vehicles/" + vehicleId).then().statusCode(404);
    }
}
