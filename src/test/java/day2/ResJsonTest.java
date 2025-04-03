package day2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ResJsonTest {

    @Test(priority = 1)
    public void json_resp()
    {
        //approach 1
        baseURI="http://localhost:3000/";
        given().
        contentType("ContentType.JSON").
        when().
        get("/db").
        then().
        statusCode(200).
        header("Content-Type","application/json; charset=utf-8").
        body("subjects[1].id",equalTo(2));
    }

}
