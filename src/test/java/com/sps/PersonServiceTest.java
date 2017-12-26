package com.sps;


import com.sps.utils.CityDetail;
import com.sps.utils.Person;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class PersonServiceTest {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/rest/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void getPersonListStatusTest() {
        given().when()
                .get("/personService/all")
                .then()
                .statusCode(200);
    }

    @Test
    public void getPersonListVerificationTest() {
        given().when()
                .get("/personService/all")
                .then()
                .body("[0].city", equalTo("London"))
                .body("[0].surname", equalTo("Aktas"))
                .body("[0].name", equalTo("Kaan"))
                .body("[0].cityDetail.county", containsString("Central"))
                .body("[0].cityDetail.postCode", equalTo("0000"))
                .body("[1].city", equalTo("Oxford"))
                .body("[1].surname", equalTo("Brown"))
                .body("[1].name", equalTo("Tom"))
                .body("[1].cityDetail.county", containsString("Central"))
                .body("[1].cityDetail.postCode", equalTo("1111"));
    }

    @Test
    public void getPersonStatusTest() {
        given().when()
                .get("/personService/Kaan")
                .then()
                .statusCode(200);

        given().when()
                .get("/personService/Hans")
                .then()
                .statusCode(404);
    }

    @Test
    public void getPersonVerificationTest() {
        given().when()
                .get("/personService/Kaan")
                .then()
                .body("surname", equalTo("Aktas"));
    }

    @Test
    public void getAddPersonTest() {
        Person person = new Person();
        person.setName("Chris");
        person.setSurname("White");
        person.setCity("London");
        CityDetail cityDetail = new CityDetail();
        cityDetail.setCounty("Tower Hill");
        cityDetail.setPostCode("2222");
        person.setCityDetail(cityDetail);

        given().contentType("application/json")
                .body(person)
                .when()
                .post("/personService")
                .then()
                .statusCode(200)
                .body("result", containsString("added successfully"));
    }

    @Test
    public void getUpdatePersonTest() {
        Person person = new Person();
        person.setName("Chris");
        person.setSurname("White");
        person.setCity("London");
        CityDetail cityDetail = new CityDetail();
        cityDetail.setCounty("Tower Hill");
        cityDetail.setPostCode("2222");
        person.setCityDetail(cityDetail);

        given().contentType("application/json")
                .body(person)
                .when()
                .put("/personService")
                .then()
                .statusCode(200)
                .body("result", containsString("updated successfully"));
    }

    @Test
    public void getDeletePersonTest() {
        Person person = new Person();
        person.setName("Chris");
        person.setSurname("White");
        person.setCity("London");
        CityDetail cityDetail = new CityDetail();
        cityDetail.setCounty("Tower Hill");
        cityDetail.setPostCode("2222");
        person.setCityDetail(cityDetail);

        given().contentType("application/json")
                .body(person)
                .when()
                .delete("/personService")
                .then()
                .statusCode(200)
                .body("result", containsString("deleted successfully"));
    }
}
