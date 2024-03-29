package com.cydeo.Day11;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;

public class ParameterizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10 })
    public void testMultipleNumbers(int number){
        System.out.println(number+ ". test" );
//        Assertions.assertTrue(number>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ" })
    public void testMultipleNames(String name){
        System.out.println("name = " + name);
    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code

    @ParameterizedTest
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035,22036,})
    public void zipCodeTest(int zipcode){

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode", zipcode)
                .log().all()
                .when().get("/us/{zipcode}")
                .then().log().all().statusCode(200);

    }


}
