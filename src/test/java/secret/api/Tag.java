package secret.api;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import secret.WeWork;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.*;

public class Tag {

    public Response addTag(String name, Object id){
        HashMap<String,Object> add = new HashMap<>();
        add.put("tagname",name);
        add.put("tagid",id);
        return given()
                .log().all()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(add)
        .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/create")
        .then()
                .body("errmsg",equalTo("created"))
                .extract().response();
    }

    public Response updateTag(String name,Object id){
        HashMap<String,Object> update = new HashMap<>();
        update.put("tagname",name);
        update.put("tagid",id);
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(update)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/update")
                .then()
                .body("errmsg",equalTo("update"))
                .extract().response();
    }

    public Response deltag(int id){


        return given()
                .param("access_token",WeWork.getInstance().getToken())
                .param("tagid",id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/delete")
                .then()
                .body("errmsg",equalTo("deleted"))
                .extract().response();

    }
}
