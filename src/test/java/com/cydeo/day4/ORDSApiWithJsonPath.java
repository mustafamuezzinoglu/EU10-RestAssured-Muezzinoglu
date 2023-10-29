package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        //get the second country name with JsonPath
        //to use jsonPath we assign response to jasonPath
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_ids
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2

        List<Object> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("allCountryName = " + countryNameWithRegionId2);
    }

    @DisplayName("GET request /employees with query param")
    @Test
    public void test2() {
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");
        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as  IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeITProgs = " + employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> employeeMakingMoreThan10000 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeeMakingMoreThan10000 = " + employeeMakingMoreThan10000);

        //get max salary first name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
    }

    }
