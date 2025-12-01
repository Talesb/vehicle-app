package com.ctw.controller;

import com.ctw.WithPostgres;
import com.ctw.dto.EngineDTO;
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
class EngineResourceIT {

    @Test
    void testCreateAndGetEngine() {
        EngineDTO engine = new EngineDTO();
        engine.setType("ELECTRIC");
        engine.setHorsepower(123);
        engine.setName("Integration Engine");

        String engineId = given()
            .contentType(ContentType.JSON)
            .body(engine)
            .when()
            .post("/engines")
            .then()
            .statusCode(201)
            .body("type", equalTo("ELECTRIC"))
            .body("horsepower", equalTo(123))
            .body("name", equalTo("Integration Engine"))
            .extract().path("id");

        given()
            .when()
            .get("/engines/" + engineId)
            .then()
            .statusCode(200)
            .body("id", equalTo(engineId))
            .body("type", equalTo("ELECTRIC"))
            .body("horsepower", equalTo(123))
            .body("name", equalTo("Integration Engine"));
    }

    @Test
    void testUpdateAndDeleteEngine() {
        EngineDTO engine = new EngineDTO();
        engine.setType("GASOLINE");
        engine.setHorsepower(200);
        engine.setName("ToUpdate");

        String engineId = given()
            .contentType(ContentType.JSON)
            .body(engine)
            .when()
            .post("/engines")
            .then()
            .statusCode(201)
            .extract().path("id");

        EngineDTO update = new EngineDTO();
        update.setType("DIESEL");
        update.setHorsepower(300);
        update.setName("UpdatedName");

        given()
            .contentType(ContentType.JSON)
            .body(update)
            .when()
            .put("/engines/" + engineId)
            .then()
            .statusCode(200)
            .body("type", equalTo("DIESEL"))
            .body("horsepower", equalTo(300))
            .body("name", equalTo("UpdatedName"));

        given()
            .when()
            .delete("/engines/" + engineId)
            .then()
            .statusCode(204);

        given()
            .when()
            .get("/engines/" + engineId)
            .then()
            .statusCode(404);
    }
}
