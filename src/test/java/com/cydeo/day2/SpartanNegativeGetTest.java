package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    //    BeforeAll is a annotation equals to @BeforeClass in TestNg, we use with static method name
    @BeforeAll
    public static void init() {
//save baseUrl inside this variable so that we dont need to type each http method
        baseURI = "http://3.83.39.224:8000";
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @Test
    public void test1() {
        Response response = given()
                                    .accept(ContentType.XML)
                            .when()
                                   .get("/api/spartans/10");

//      verify status code 406
        assertEquals(406,response.statusCode());

//        verify content type
        assertEquals(response.contentType(), "application/xml;charset=UTF-8");


    }


}
