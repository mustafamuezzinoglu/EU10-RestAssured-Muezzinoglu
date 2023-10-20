package com.cydeo.Day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParametrizedTest {

    // Write a parameterized test for this request
    // Get the data from csv source
    // GET https://api.zippopotam.us/us/{state}/{city}

    @ParameterizedTest
    @CsvFileSource(resources = "/postalcode.csv", numLinesToSkip = 1)
    public void zipCodeTestWithFile(String stateArg, String cityArg, int zipCountArg){
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("zipCountArg = " + zipCountArg);

        given()
                .baseUri("http://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", stateArg)
                .pathParam("city", cityArg)
                .log().uri()
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .body("places", hasSize(zipCountArg));



    }

}