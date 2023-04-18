package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {

    //    BeforeAll is a annotation equals to @BeforeClass in TestNg, we use with static method name
    @BeforeAll
    public static void init() {
//save baseUrl inside this variable so that we dont need to type each http method
        baseURI = "http://3.83.39.224:8000";

        String dbUrl = "jdbc:oracle:thin:@3.83.39.224:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll
    public static void tearDown() {
        DBUtils.destroy();
    }
}
