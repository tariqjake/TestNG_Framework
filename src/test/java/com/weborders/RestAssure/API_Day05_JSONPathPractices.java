package com.weborders.RestAssure;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class API_Day05_JSONPathPractices {

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

    @Test
    public void simpleGetRequest(){
        given()
                .relaxedHTTPSValidation()
        .when()
                .queryParam("per_page",1)
                .get("posts")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    public JsonPath getJSONPath(String pathParameter){
       Response response =
               given()
                       .relaxedHTTPSValidation().
               when()
                       .get(pathParameter);

       return response.jsonPath();
    }



    @Test
    public void printTheFirstAuthor(){
        JsonPath jspath = getJSONPath("posts");
        String author = jspath.getString("[0].author");
        List<Integer> listNum = jspath.getList("author", Integer.class);
        System.out.println(listNum);

    }

    @Test
    public void GpathExercises(){
        Response response = given().relaxedHTTPSValidation().get("http://ergast.com/api/f1/drivers.json");
        JsonPath jp = response.jsonPath();

        List<Object> list = jp.getList("MRData.DriverTable.Drivers");

        System.out.println(list);

        Map<String, String> map = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);

        System.out.println(map.keySet());

        Object o = jp.get("MRData.DriverTable.Drivers.findAll{it.givenName=='George' && it.nationality=='American'}");
        System.out.println(o);

        System.out.println(( Object) jp.get("MRData.DriverTable.Drivers.findAll{driver-> driver.givenName=='George' && driver.nationality=='British'}"));


        //The list of the drivers whose firstname length is 3
        List<Object> driversShortName = jp.get("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName.length()==3 }");
        System.out.println(driversShortName);

        //The list of the lastnames of the drivers whose firstname length is 3
        List<Object> filteredFamilyName = jp.get("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName.length()==3 }.familyName");
        System.out.println(filteredFamilyName);
    }


    @Test
    public void testPOJO(){

        Response response = given().relaxedHTTPSValidation().get("http://ergast.com/api/f1/drivers.json");
        JsonPath jp = response.jsonPath();

        Driver drObj = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);
        System.out.println(drObj);

    }


}
