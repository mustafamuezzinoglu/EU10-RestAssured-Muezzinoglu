package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestPostGetPutPatchAndDelete extends SpartanTestBase {

    @Test
    public void test(){

        String requestJsonBody = "{\"gender\":\"Female\",\n" +
                "\"name\":\"eso\",\n" +
                "\"phone\":9876543210}";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans")
                .then().statusCode(201).extract().response();

        Response responsePost = get("api/spartans").then().statusCode(200).extract().response();
        int id = responsePost.path("id[-1]");
        System.out.println("it is created a spartan with this id= " +id + " as below\n " +requestJsonBody);

        //just like post request we have different options to send body we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 9876543210L);

        given()
//                .accept(ContentType.JSON)  we dont need to specify accept because we wont get any json body
                    .contentType(ContentType.JSON)//hey api I am sending JSON body
                    .body(putRequestMap)
                    .and().pathParam("id", id)
                    .when().put("/api/spartans/{id}")
                    .then().statusCode(204);

        Response responseUpdated = given().accept(ContentType.JSON)
                    .and().pathParam("id", id)
                    .when().get("/api/spartans/{id}");

        String name = responseUpdated.path("name");
        System.out.println("This spartan is updated with this name= " + name);

        assertThat(putRequestMap.get("name"), is(name));

        //send a get request after update, make sure updated filed changed, or the new info matching with requestBody that we send
        //just like post request we have different options to send body we will go with map

        Map<String,Object> patchRequestMap = new LinkedHashMap<>();
        patchRequestMap.put("phone", 2589631477L);

                    given() .contentType(ContentType.JSON)//hey api I am sending JSON body
                    .body(patchRequestMap)
                    .and().pathParam("id", 147)
                    .when().patch("/api/spartans/{id}")
                    .then()
                    .statusCode(204);

        Long phoneNumber = responseUpdated.path("phone");
        System.out.println("And then  updated with this phone number= " + phoneNumber);

        assertThat(putRequestMap.get("phone").toString(), is(responseUpdated.path("phone").toString()));

        //send a get request after update, make sure updated filed changed, or the new info matching
        //with requestBody that we send

                    given().pathParam("id", id)
                    .when().delete("/api/spartans/{id}")
                    .then().statusCode(204);
        System.out.println("lastly the spartan that is created already is deleted");

        //send a get request after you delete make sure you are getting 204

        }


}
