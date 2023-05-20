package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class govXmlTest {

    @Test
    public void test1(){

        //send a get request to
        //https://data.ct.gov/api/views/kf75-36tt/rows.xml

        Response response = given()
                .when()
                .get("https://data.ct.gov/api/views/kf75-36tt/rows.xml")
                .then().statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        //get all the years
        List<Integer> years = xmlPath.getList("response.row.row.fiscal_year");
        System.out.println("years = " + years);
        //get all aenglc_rank
        List<Integer> aenglc_rank = xmlPath.getList("response.row.row.aenglc_rank");
        System.out.println("aenglc_rank = " + aenglc_rank);
        //get 109 collection_size
        int collection_size109 = xmlPath.getInt("response.row.row[0].collection_size");
        System.out.println("collection_size109 = " + collection_size109);

        //get 19 -address
        String address19 = xmlPath.getString("response.row.row[3].@_address");
        System.out.println("address19 = " + address19);

    }
}
