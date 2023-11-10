package com.cydeo.day12;

import com.cydeo.utilities.BookitTestBase;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class BookitSpecTest extends BookitTestBase {

    @Test
    public void Test1(){
        //send a get request to api/users/me endpoint as a teacher
        //verify status code and content type
    given()
                .spec(teacherReqSpec)
            .when()
                 .get("api/users/me")
            .then()
                .spec(responseSpec)
            .spec(userCheck("Janette", "Baskett"));

    }
    @Test
    public void Test2(){
            //send a get request to api/users/me endpoint as a student member
            //verify status code and content type
        given()
                .spec(studentMemberReqSpec)
            .when()
                .get("api/users/me")
            .then()
                .spec(getDynamicResSpec(200))
        .body("firstName", is("Marius"));
    }
}
