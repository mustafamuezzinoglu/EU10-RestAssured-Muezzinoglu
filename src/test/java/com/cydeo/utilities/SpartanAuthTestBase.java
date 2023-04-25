package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {


    //    BeforeAll is a annotation equals to @BeforeClass in TestNg, we use with static method name
    @BeforeAll
    public static void init() {
//save baseUrl inside this variable so that we dont need to type each http method
        baseURI = "http://3.83.39.224:7000";

    }

}
