package com.cydeo.Day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //beforeClass for TestNg
    @BeforeAll
    public static void init(){
        System.out.println("Before all is running");
    }

    //@BeforeMethod is testNg version of beforeEach, same logic
    @BeforeEach
    public void initEach(){
        System.out.println("\tBefore each is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("After each is running");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }

    @AfterAll
    public static void close(){
        System.out.println("After all is running");
    }
}
