package com.cydeo.Day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodSourceParameterizedTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        System.out.println("name = " + name);

    }

    public static List<String> getNames() {
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs

        List<String> nameList = Arrays.asList("Parvin", "Nasim", "mohamad", "Nadir", "Saim", "Gurhan", "Murodil");
        return nameList;
    }

    public static List<Map<String, String>> getExcelData(){
        //get your file object
        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/VyTrackTestData.xlsx", "QA3-all" );

        //return sheet as a list of map
        return vyTrackFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public  void excelParamTest(Map<String, String> userInfo){

        System.out.println("First name: " + userInfo.get("firstname"));
        System.out.println("Last name: " + userInfo.get("lastname"));
    }

}
