package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @DisplayName("Assertion with number")
    @Test
    public void test1() {

        //actual 5+5
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));

        //number comparison
//        greaterThan()
//        greaterThanOrEqualTo()
//        lessThan()
//        lessThanOrEqualTo()
        assertThat(5 + 5, greaterThan(9));
    }

    @DisplayName("Assertion with String")
    @Test
    public void test2() {

        String text = "EU10 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text, is("EU10 is learning Hamcrest"));
        assertThat(text, equalTo("EU10 is learning Hamcrest"));
        assertThat(text, is(equalTo("EU10 is learning Hamcrest")));

        // checking if this text starts with EU10
        assertThat(text, startsWith("EU10"));
        //now do it in case insensitive manner
        assertThat(text, startsWithIgnoringCase("EU10"));
        //endswith
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));
        //with ignoreing case
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = " ";

        //check if above string is blank
        assertThat(str, blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());
    }

    @DisplayName("Hamscret for Collection")
    @Test
    public void testCollection() {

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);
        System.out.println("listOfNumbers.size() = " + listOfNumbers.size());

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));
        //check if this list hasItem 77
        assertThat(listOfNumbers, hasItem(77));
        //chech ıf thıs lıst hasItems 77,54,23
        assertThat(listOfNumbers, hasItems(77,54,23));

        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

        assertThat(listOfNumbers,everyItem(lessThan(100)));

    }



    }
