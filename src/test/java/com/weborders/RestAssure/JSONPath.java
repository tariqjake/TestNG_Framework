package com.weborders.RestAssure;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JSONPath {
    Faker faker = new Faker();

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://www.tariq.dev.cc/wp-json";
        RestAssured.basePath = "/wp/v2/";
    }

    @Test
    public void test(){
        createRandomUser();
    }



    public int createRandomUser(){

        Response response =

                given().
                        relaxedHTTPSValidation().
                        auth().preemptive().basic("admin","admin").
                when().
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        body("{\n" +
                                "\t\"username\" : \""+faker.name().username()+"\" ,\n" +
                                "\t\"email\" : \""+faker.internet().emailAddress()+"\" ,\n" +

                                "\t\"password\" : \""+faker.internet().password()+"\" \n" +
                                "}").
                        post("users");

        int newId = response.jsonPath().getInt("id");
        return newId;

    }

}
