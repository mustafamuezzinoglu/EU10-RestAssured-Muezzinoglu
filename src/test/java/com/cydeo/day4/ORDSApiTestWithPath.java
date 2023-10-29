package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print limit hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first countryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print  "href": "http://3.83.39.224:1000/ords/hr/countries/CA"
        String canadasLink = response.path("items[2].links[0].href");
        System.out.println("canadasLink = " + canadasLink);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionsIds = response.path("items.region_id");
        for (Integer regionsId : allRegionsIds) {
            System.out.println("regionsId = " + regionsId);
            assertEquals(2, regionsId);
        }
    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");
        for (String jobId : allJobIDs) {
            System.out.println("jobId = " + jobId);
            assertEquals("IT_PROG", jobId);
        }
    }

    //TASK
    //print name of each IT_PROGs

    @DisplayName("GET request to /employees each name of IT_PROGs with Query Param")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> firstNames = response.path("items.first_name");

        for (String eachName : firstNames){

            System.out.println("eachName = " + eachName);
        }

    }


}
