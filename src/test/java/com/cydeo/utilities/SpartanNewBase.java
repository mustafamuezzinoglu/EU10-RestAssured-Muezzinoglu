package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    public static RequestSpecification adminRequestSpec;
    public static RequestSpecification userRequestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.83.39.224";
        port =7000;
        basePath = "/api";

        adminRequestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userRequestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL); //logging with response specification

    }

    @AfterAll
    public static void close(){
        //reset the info we set above , method come from restassured
        reset();

    }

}
