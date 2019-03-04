package com.weborders.RestAssure;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserRestAssureAPI {
    @BeforeClass
    public void setUP(){
        RestAssured.baseURI = "https://www.tariq.dev.cc/wp-json";
        RestAssured.basePath = "wp/v2";
    }



    @Test
    public void checkTheAccesOfAUser(){
        given().
                relaxedHTTPSValidation().

        when().
                get("users").

        then().
                statusCode(200).
                log().all().
                body("id",hasSize(1)).
                body("id",hasItem(1));
    }

    @Test
    public void verificationOfUserNotAbleToCreateUsers(){
        given().
                relaxedHTTPSValidation().

        when().
                contentType(ContentType.JSON).
        and().
                body("{\n" +
                        "\t\"username\" : \"user_a\" ,\n" +
                        "\t\"name\" : \"user a\" ,\n" +
                        "\t\"first_name\" : \"super\", \n" +
                        "\t\"last_name\" : \"user \" ,\n" +
                        "\t\"email\" : \"s@g.com\" ,\n" +
                        "\t\"roles\" : \"author\" ,\n" +
                        "\t\"password\" : \"user\" \n" +
                        "\t\n" +
                        "}").

                post("users").

        then().
                statusCode(401).
                log().all();
    }


    @Test
    public void adminUser_ShouldBeAbleto_UpdateExistingUser(){
        given().
                relaxedHTTPSValidation().
                auth().preemptive().basic("admin","admin").

        when().
                contentType(ContentType.JSON).
                body("{\n" +
                        "\t\"name\" : \"user UPDATED NAME\"\n" +
                        "}").
                pathParam("updateID", 4).
                put("users/{updateID}").
        then().
                log().all().
                statusCode(200).
                body("name",is("user UPDATED NAME"));


    }

    @Test
    public void adminUser_ShouldBeAbleto_CreateNewUser(){
        given().
                relaxedHTTPSValidation().
                auth().preemptive().basic("admin","admin").
                contentType(ContentType.JSON).

        when().
                body("{\n" +
                        "\t\"username\" : \"user_NEW\" ,\n" +
                        "\t\"email\" : \""+ new Faker().internet().emailAddress() +"\" ,\n" +
                        "\t\"password\" : \"user\" \n" +
                        "}").

                post("users").

        then().
                log().all().
                statusCode(201).
                body("username",is("user_NEW"));


    }

    @Test
    public void adminUser_ShouldBeAbleto_DeleteExistingUser(){
        given().
                relaxedHTTPSValidation().
                auth().preemptive().basic("admin","admin").
                contentType(ContentType.JSON).

        when().
                queryParam("force",true).
                param("reassign",1).
                pathParam("id",9).
                delete("users/{id}").

        then().
                log().all().
                statusCode(200).
                body("deleted",is(true));
    }

    @Test
    public void publicUser_ShouldNotBeAbleto_ViewExistingUser_otherThanAdmin(){
        given().
                relaxedHTTPSValidation().
                auth().preemptive().basic("contributor","contributor").
                pathParam("id",3).

        when().
                get("users/{id}").

        then().
                log().all().
                statusCode(403).
                body("message",is("Sorry, you are not allowed to list users."));
    }


}
