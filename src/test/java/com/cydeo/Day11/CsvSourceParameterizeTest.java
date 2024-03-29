package com.cydeo.Day11;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CsvSourceParameterizeTest {

   // Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19
    @ParameterizedTest
    @CsvSource({ "  1, 3 ,  4",
                 "  2, 3 ,  5",
                 "  8, 7 , 15",
                 " 10, 9 , 19"  } )
    public void testAddition(int num1, int num2, int sum){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        assertThat(num1 + num2, equalTo(sum));

    }
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "VA, McLean",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request
    @ParameterizedTest
    @CsvSource({ "NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "VA, McLean",
            "MD, Annapolis"  } )
    public void zipCodeMultiInputTest(String state, String city){

      given().accept(ContentType.JSON)
             .pathParam("state", state)
             .pathParam("city", city)
             .get("https://api.zippopotam.us/us/{state}/{city}")
              .then().statusCode(200);

        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .log().uri()
                .when().get("/us/{state}/{city}")
                .then()
                .and().body("places.'place name'", everyItem(containsStringIgnoringCase(city))) //if there is any space in the key we sould cover in a single coat
                .statusCode(200)
//                .log().body()
                .extract().jsonPath().getList("places").size();

         System.out.println("placeNumber = " + placeNumber);

    }
}
