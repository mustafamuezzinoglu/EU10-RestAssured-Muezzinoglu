package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;


public class CYDEOTrainingApiWithJsonPath {

    //  BeforeAll is a annotation equals to @BeforeClass in TestNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseUrl inside this variable so that we dont need to type each http method

        baseURI = "https://api.training.cydeo.com";

        //get ip address from configuration
    }

    @DisplayName("GET request to individual student")
    @Test
    public void test1() {

        //send a get request to student id 14 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Glen
                batch 3
                section N/A
                emailAddress lroutham0@opera.com
                companyName Gabtune
                state New Jersey
                zipCode 72475

                using JsonPath
             */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 14)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.header("Content-Type"));

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println("firstName = " + firstName);
        assertEquals("Glen", jsonPath.getString("students[0].firstName"));

        int batch = jsonPath.getInt("students[0].batch");
        System.out.println("batch = " + batch);
        assertEquals(3, jsonPath.getInt("students[0].batch"));

        String zipCode = jsonPath.getString("students.company.address[0].zipCode");
        System.out.println("zipCode = " + zipCode);

    }

}
