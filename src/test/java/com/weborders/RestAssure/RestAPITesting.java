package com.weborders.RestAssure;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAPITesting {


    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://www.tariq.dev.cc/wp-json";
        RestAssured.basePath = "/wp/v2/";
    }


    @Test
    public void simpleGetRequest(){
        given()
                .relaxedHTTPSValidation()
        .when()
              .log().all()
              .queryParam("per_page",1)
              .get("posts")
        .then()
             .log().all()
             .assertThat()
            .statusCode(200)
            .body("id",equalTo(6))
            .body("title.rendered",is("canimsin"))
            .body("date",startsWith("2019-02"));
    }

    //TASK : Check your response ID and title is as expected in your app
    @Test
    public void simpleGetRequest2(){
        given()
                .relaxedHTTPSValidation()
                .when()
                .log().all()
                .queryParam("per_page",1)
                .get("posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("id",hasItem(23))
                .body("title.rendered",hasItem("Sanada gunaydin"))
                .body("date",hasItem(startsWith("2019-03")));
    }


    @Test
    public void printBody(){

        given()
                .relaxedHTTPSValidation()
        .when()
                //.log().all()
        .get("posts")
                .body().prettyPrint();
    }

    //Path Parameters
    @Test
    public void simpleGetRequest3(){
        given()
                .relaxedHTTPSValidation()
        .when()
                .log().all()
                .pathParam("id",1)
                .get("posts/{id}")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void firstPostRequest(){
        given()
               .relaxedHTTPSValidation()
        .when()
               .auth().preemptive().basic("admin","admin")
               .contentType(ContentType.JSON)
        .and()
               .body("{\n" +
                       "\t\"title\" : \"REST API POST\",\n" +
                       "\t\"author\" : 1,\n" +
                       "\t\"status\" : \"publish\"\n" +
                       "}")
               .post("posts")
        .then()
               .log().all()
               .statusCode(201);
    }


    @Test
    public void simplePutRequest(){
        given()
               .relaxedHTTPSValidation()

        .when()
               .auth().preemptive().basic("admin","admin")
               .contentType(ContentType.JSON)

        .and()
               .body("{\n" +
                       "\"title\" : \"UPDATED REST API TITLE\"\n" +
                       " }")
               .pathParam("updatedID",24)
               .put("posts/{updatedID}")

        .then()
               .log().all()
               .statusCode(200)
               .body("title.rendered",is("UPDATED REST API TITLE"));

    }


    @Test
    public void simpleDeleteTest(){
        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("admin","admin")

        .when()
                .pathParam("deleteID",24)
                .delete("posts/{deleteID}")

        .then()
                .log().all()
                .statusCode(200);


    }

    @Test
    public void simpleDeleteInWordsPressPerminentlyTest(){
        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("admin","admin")

                .when()
                .pathParam("deleteID",24)
                .queryParam("force",true)
                .delete("posts/{deleteID}")

                .then()
                .log().all()
                .statusCode(200)//will be 404 since you have already run once and it is deleted
                .body("delete",is(true));


    }





}
