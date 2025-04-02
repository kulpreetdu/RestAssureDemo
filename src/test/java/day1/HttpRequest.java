package day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class HttpRequest {

    @Test
    public void test_1()
    {
        Response res= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(res.statusCode());
    }

    @Test
    public void test_2()
    {
        baseURI="https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200).body("data[1].id",equalTo(8));
    }

    @Test
    public void test_body()
    {
        baseURI ="https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200).body("data[4].first_name",equalTo("George")).
            body("data.first_name",hasItems("George","Rachel"));

    }

    @Test
    public void test_post()
    {
        Map<String, List<Object>> map= new HashMap<String,List<Object>>();
        List<Object> values= new ArrayList<Object>();
        values.add("Test");
        values.add("1");
        map.put("name",values);
        map.put("job",values);

        System.out.println(map);
        JSONObject req= new JSONObject(map);
        System.out.println(req);
    }

    @Test
    public void test_post1()
    {
        JSONObject req= new JSONObject();
        req.put("name","test");
        req.put("job", "teacher");
        baseURI="https://reqres.in/api";
        given().body(req.toJSONString()).when().post("/users").then().statusCode(201);
        System.out.println(req.toJSONString());
    }

    @Test
    public void test_put()
    {
        JSONObject req= new JSONObject();
        req.put("name","test");
        req.put("job", "teacher");
        baseURI="https://reqres.in/api";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().put("/users/2").then().statusCode(200);
        System.out.println(req.toJSONString());
    }

    @Test
    public void test_patch()
    {
        JSONObject req= new JSONObject();
        req.put("name","test");
        req.put("job", "teacher");
        baseURI="https://reqres.in/api";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().patch("/users/2").then().statusCode(200);
        System.out.println(req.toJSONString());
    }

    @Test
    public void test_delete()
    {
        /*

        JSONObject req= new JSONObject();
        req.put("name","test");
        req.put("job", "teacher");
        */
        baseURI="https://reqres.in/api";
        //given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().delete("/users/2").then().statusCode(204);
        //System.out.println(req.toJSONString());
    }

}
