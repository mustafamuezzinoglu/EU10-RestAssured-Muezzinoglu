package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanSpecTestOriginal extends SpartanNewBase {

    //all tests in this class will use admin credentials
    //al tests in this class will expect json results from response

    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

    }

    @Test
    public void test2(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .pathParam("id", 15 )
                .log().all()
        .when()
                .get("/spartans/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }
}
