package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    int id = 167;

    @DisplayName("PUT request to spartan")
    @Test
    public void PUTRequest(){

        //just like post request we have different options to send body we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1234631100L);

        System.out.println(putRequestMap.get("phone"));


                given()
//                .accept(ContentType.JSON)  we dont need to specify accept because we wont get any json body
                .contentType(ContentType.JSON)//hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", id)
                .when().put("/api/spartans/{id}")
                 .then().statusCode(204);

        Response responseUpdated = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/api/spartans/{id}");

        assertThat(putRequestMap.get("phone").toString(), is(responseUpdated.path("phone").toString()));

        //send a get request after update, make sure updated filed changed, or the new info matching with requestBody that we send
//

    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body we will go with map
        Map<String,Object> patchRequestMap = new LinkedHashMap<>();
        patchRequestMap.put("phone", 2589631477L);

        given() .contentType(ContentType.JSON)//hey api I am sending JSON body
                .body(patchRequestMap).log().body()
                .and().pathParam("id", id)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a get request after update, make sure updated filed changed, or the new info matching
        //with requestBody that we send
    }

    @DisplayName("DELETE one spartan")
    @Test

    public void deleteSpartan(){

        given().pathParam("id", id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 204



    }
}
