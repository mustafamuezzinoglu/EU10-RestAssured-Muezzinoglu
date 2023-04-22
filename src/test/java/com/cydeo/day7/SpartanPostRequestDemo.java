package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanPostRequestDemo extends SpartanTestBase {
       /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
    @Test
    public void postMethod1(){

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Nike\",\n" +
                "\"phone\":1234567890}";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");


//verify status code
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getContentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        String actualResponseMessage = response.path("success");

        assertEquals(expectedResponseMessage, actualResponseMessage);

        assertThat(response.path("data.name"), is("Nike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(1234567890L));


    }

    @DisplayName("POST with Map to Json")
    @Test
    public void postMethod2(){

//        create a map to keep request json body information
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Nike");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 1234567890L);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");


//verify status code
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getContentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat( response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Nike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(1234567890));

        response.prettyPrint();



    }


}
