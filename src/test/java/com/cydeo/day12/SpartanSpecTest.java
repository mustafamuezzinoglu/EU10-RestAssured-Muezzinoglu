package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanSpecTest extends SpartanNewBase {

    //all tests in this class will use admin credentials
    //al tests in this class will expect json results from response

    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1(){
//
//        RequestSpecification requestSpec = given()
//                .accept(ContentType.JSON)
//                .and()
//                .auth().basic("admin", "admin")
//                .log().all();
//
//        ResponseSpecification responseSpec = expect().statusCode(200)
//                                                .and()
//                                                .contentType(ContentType.JSON)
//                                                .logDetail(LogDetail.ALL); //logging with response specification

        given()
                .spec(adminRequestSpec)
        .when()
                .get("/spartans")
        .then()
                .spec(responseSpec);
    }

    @Test
    public void test2(){

        given()
                .spec(adminRequestSpec)
//                .accept(ContentType.JSON)
//                .and()
//                .auth().basic("admin", "admin")
                .pathParam("id", 15 )
                .log().all()
        .when()
                .get("/spartans/{id}")
        .then()
                .spec(responseSpec)
//                .statusCode(200)
//                .and()
//                .contentType(ContentType.JSON)
        ;
    }


    @Test
    public void test3(){

        given()
                .spec(adminRequestSpec)
                .queryParams("nameContains","j", "gender", "Female" )
       .when()
                .get("/spartans/search")
      .then()
                .spec(responseSpec)
                .body("numberOfElements", is(6));
    }
}
