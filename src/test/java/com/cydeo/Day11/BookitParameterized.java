package com.cydeo.Day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookitParameterized {

public static List<Map<String,String>> getExcelData(){
    ExcelUtil bookitFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");

    return bookitFile.getDataList();
}

@ParameterizedTest
    @MethodSource("getExcelData")
    public void testGetExcelData(Map<String, String> user) {

    System.out.println("user.get(\"email\") = " + user.get("email"));
    System.out.println("user.get(\"password\") = " + user.get("password"));

    given()
            .accept(ContentType.JSON)
            .baseUri("https://api.qa2.bookit.cydeo.com")
            .queryParams(user) //i pass map directly because query param keys and map keys are equal
            .when().get("/sign")
            .then().statusCode(200)
            .log().body();
}
}
