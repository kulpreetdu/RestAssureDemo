package day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class LocalApiTest {


    @Test
    public void lapi()
    {
        baseURI="http://localhost:3000/";
        given().get("/users").then().statusCode(200);
    }


    @Test
    public void lapi_post()
    {
        JSONObject req= new JSONObject();
        req.put("firstname","testfir");
        req.put("lastname","testlast");
        req.put("subject","sub1");
        req.put("id",5);
        baseURI="http://localhost:3000/";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().post("/users").
                then().statusCode(201);
    }

    @Test
    public void lapi_put()
    {
        JSONObject req= new JSONObject();
        req.put("firstname","testalpha");
        req.put("lastname","testlapla");
        req.put("subject","subaplha");
        req.put("id","idalpha");
        baseURI="http://localhost:3000/";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().put("/users/1").
                then().statusCode(200);
    }

    @Test
    public void lapi_patch()
    {
        JSONObject req= new JSONObject();
        req.put("firstname","testpatch");
        //req.put("lastname","testlapla");
        //req.put("subject","subaplha");
        //req.put("id","idalpha");
        baseURI="http://localhost:3000/";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
                when().patch("/users/1").
                then().statusCode(200);
    }

    @Test
    public void lget()
    {
        baseURI="http://localhost:3000/";
        given().get("/users").then().statusCode(200);;
    }

    @Test
    public void lgetqu()
    {
        baseURI="http://localhost:3000/";
        given().
                pathParam("mypath","users").
                queryParam("subject","sub").
        when().
                get("{mypath}").
        then().
                statusCode(200);
                //log().all();
    }

    @Test
    public void get_cookie()
    {
        baseURI="http://localhost:3000/";
        Response res= given().
                when().get("/users");
        System.out.println(res);
        Map<String,String> cookie_values= res.getCookies();
        System.out.println(cookie_values.keySet());
        System.out.println(cookie_values.values());
    }
}
