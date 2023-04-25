package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to spartan")
    @Test
    public void PUTRequest(){

        //just like post request we have differnt options to send body we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1234631477L);

        given() .contentType(ContentType.JSON)//hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 129)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a get request after update, make sure updated filed changed, or the new info matching
        //with requestBody that we send

    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 2589631477L);

        given() .contentType(ContentType.JSON)//hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 129)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a get request after update, make sure updated filed changed, or the new info matching
        //with requestBody that we send
    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){

        int idToDelete = 129;

        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 204



    }
}
