package com.weborders.RestAssure;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.*;

public class TestWithHamCrest {

    @Test
    public void test1(){
        assertThat(4, equalTo(4));
        assertThat("abc",is("abc"));
        assertThat("abc",not(equals("abcdfgh")));

        List<Integer> lst = Arrays.asList(2,3,4,5,6);
        assertThat(lst, hasSize(4));
        assertThat("canan",containsString("a"));
        assertThat(lst,everyItem(greaterThan(1)));

    }

}
