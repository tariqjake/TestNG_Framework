package com.weborders.RestAssure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class serializationDeserialization {

    @Test
    public void dataBindTest() throws IOException {

        //Converting a JSON -> JAVA
        String json = "{\"name\" : \"Adam\", \"age\" : 21}";
        ObjectMapper obm = new ObjectMapper();
        Person person = obm.readValue(json, Person.class);

        System.out.println(json);
        System.out.println(person);


        //Converting JAVA -> JSON
        String convertedToJava = obm.writeValueAsString(person);
        System.out.println(convertedToJava);


    }


    @Test
    public void dataBindDriver() throws IOException {

      String jsStr = "{\n" +
              "                    \"driverId\": \"abate\",\n" +
              "                    \"url\": \"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\",\n" +
              "                    \"givenName\": \"Carlo\",\n" +
              "                    \"familyName\": \"Abate\",\n" +
              "                    \"dateOfBirth\": \"1932-07-10\",\n" +
              "                    \"nationality\": \"Italian\"\n" +
              "                }";

      ObjectMapper obm = new ObjectMapper();
      Driver driver = obm.readValue(jsStr,Driver.class);
        System.out.println(driver);



        String toJSStr = obm.writeValueAsString(driver);
        System.out.println(toJSStr);


    }


}

@JsonIgnoreProperties(ignoreUnknown = true)
class Person {

    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}