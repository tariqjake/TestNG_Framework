package com.weborders.RestAssure;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestTest {
    // Given rest end point https://postman-echo.com/time/leap?timestamp=2016-10-10
    //When i send a HTTP Get request to the server
    // Then i should het 200K result as status code


    @Test
    public void firstTest(){

        when()
            .get("https://postman-echo.com/time/leap?timestamp=2016-10-10")
        .then()
                .statusCode(200 );
    }


    @Test
    public void secondTest(){
        RestAssured.baseURI = "https://www.tariq.dev.cc/wp-json";
        RestAssured.basePath = "wp/v2";

        given().
                relaxedHTTPSValidation()
        .when()
                .get("posts")
        .then()
                .statusCode(200 );
    }


    // Given rest end point https://www.tariq.dev.cc/wp-json/wp/v2/posts/6
    //When i send a HTTP Get request to the server
    // Then i should het 200K result as status code

    @Test
    public void idValidation(){


        given().
                relaxedHTTPSValidation()
                .when()
                .get("https://www.tariq.dev.cc/wp-json/wp/v2/posts/{id}",6)
        .then()
                .statusCode(200 )
                .and().log().all().and()
                .body("id",equalTo(6))
                .body("title.rendered",equalTo("canimsin"));
    }


}
