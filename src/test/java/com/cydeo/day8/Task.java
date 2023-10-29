package com.cydeo.day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Task {

    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
*/
    @DisplayName("admin and editor should crud but user only read expected 200,201 ")
    @Test
    public void test4(String username,String password){

        given().accept(ContentType.JSON)
                .auth().basic(username,password)
                .and()
                .post("api/spartans");






    }

}
