package secret;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.fromContentType;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestWework {
    static String token;
    static int parentid = 1;

    @BeforeClass
    public static void getToken(){
        token = given()
                .param("corpid","ww2e9b02771240c625")
                .param("corpsecret","zyXR7JHt49xwLQmLx2ZeUmWhDKvnE6RGWWeg7EFNTCQ")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract()
                .body().path("access_token");
        System.out.println(token);
    }

    @Test
    public void departCreat(){
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "设计部");
        data.put("parentid",parentid);

        given()
                .log().all()
                .queryParam("access_token",token)
                .contentType(ContentType.JSON)
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all()
                .body("errcode",equalTo(0));
        System.out.println(token);


    }
}
