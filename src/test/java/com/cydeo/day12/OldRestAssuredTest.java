package com.cydeo.day12;
import com.cydeo.utilities.*;
import io.restassured.filter.log.*;
import io.restassured.http.*;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OldRestAssuredTest extends SpartanNewBase{

    @Test
    public void getAllSpartans(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
        .when()
                .get("spartans")
        .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1)) // in this test if its failed somewhere it will stop the test
                .body("id[5]", is(6)) // and its not going on
                .log().all();
    }

        @Test
    public void test2(){
/*
in previous version of RestAssured, the given when then style was actually
written in given expect when format.
it will assert all the result and give one answer and does not fail whole thing
if first one fail unlike new structure
 */
            given()
                    .accept(ContentType.JSON)
                    .and()
                    .auth().basic("admin", "admin")
                    .log().all()
            .expect()
                    .statusCode(200)
                    .and()
                    .contentType("application/json")
                    .body("id[0]", is(1))
                    .body("id[5]", is(6))
                    .logDetail(LogDetail.ALL) // log way using with expect()
            .when()
                    .get("/spartans");
    }

}
