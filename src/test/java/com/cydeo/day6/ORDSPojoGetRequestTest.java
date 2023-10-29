package com.cydeo.day6;

import com.cydeo.pojo.*;
import com.cydeo.utilities.HRTestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = //given().accept(ContentType.JSON) we can also add this part
                // .when(). and also we can add this part
                get("/regions")
                .then().statusCode(200)
                        .log().body()
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegionName());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

        Link link1 = region1.getLinks().get(0);
        System.out.println("link1 = " + link1);
    }

    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){

        Employee  employee1 = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);
    }

    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields
     */

    @Test
    public void regionPojoTest() {

//send a get request and save everthing inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path so as() method is enough
        Regions regions = get("/regions")
                        .then().statusCode(200)
                        .extract().as(Regions.class);
        //verify count is 4
        assertThat(regions.getCount(), is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items = regions.getItems();

//loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRId());
            regionNames.add(region.getRegionName());
        }
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionIds, is(expectedRegionIds ));
        assertThat(regionNames, is(expectedRegionNames));




    }

}
